package com.chat.community.core.repository;

import com.chat.community.core.model.user.User;
import com.chat.community.core.model.user.Email;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @NotNull
    User save(@NotNull User user);

    Optional<User> findByEmail(Email email);

}
