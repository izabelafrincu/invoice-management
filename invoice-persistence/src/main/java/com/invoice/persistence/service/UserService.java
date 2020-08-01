package com.invoice.persistence.service;

import com.invoice.persistence.dto.TransactionDto;
import com.invoice.persistence.enums.TransactionMember;
import com.invoice.persistence.model.User;
import com.invoice.persistence.repository.UserRepository;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class UserService {
  private UserRepository userRepository;

  protected Long saveUser(TransactionMember transactionMember, TransactionDto.ClientDto clientDto) {
    User user = createUser(transactionMember, clientDto);
    userRepository.save(user);

    return user.getId();
  }

  @Transactional
  private User createUser(TransactionMember transactionMember, TransactionDto.ClientDto clientDto) {
    return userRepository.findByCNP(clientDto.getCNP())
        .map(existingUser -> updateExistingUser(transactionMember, clientDto, existingUser))
        .orElse(createNewUser(transactionMember, clientDto));
  }

  @Transactional
  protected User findUserByCnp(String CNP) {
    return userRepository.findByCNP(CNP)
        .orElseThrow(() -> new IllegalArgumentException(String.format("User with CNP=%s not found", CNP)));
  }

  private User updateExistingUser(TransactionMember transactionMember, TransactionDto.ClientDto clientDto,
      User existingUser) {
    User newUser = new User(existingUser.getCNP(), clientDto.getName());
    newUser.setId(existingUser.getId());
    setAccountId(transactionMember, clientDto, newUser);

    return newUser;
  }

  private User createNewUser(TransactionMember transactionMember, TransactionDto.ClientDto clientDto) {
    User user = new User(clientDto.getCNP(), clientDto.getName());
    setAccountId(transactionMember, clientDto, user);
    return user;
  }

  private void setAccountId(TransactionMember transactionMember, TransactionDto.ClientDto clientDto, User user) {
    switch (transactionMember) {
      case WALLET:
        user.setWalletId(UUID.fromString(clientDto.getAccountId()));
        break;
      default:
        user.setIBAN(clientDto.getAccountId());
        break;
    }
  }
}