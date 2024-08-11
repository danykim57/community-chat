package com.chat.community.core.model.user;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.regex.Pattern.matches;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Embeddable
@Getter
public class Email {

    private final String address;

    public Email() {
        this.address = null;
    }

    public Email(String address) {
        checkArgument(isNotEmpty(address), "address cannot be empty");
        checkArgument(address.length() >= 4, "address must be at least 4 characters");
        checkArgument(checkAddress(address), "Invalid email address: " + address);
        this.address = address;
    }

    private static boolean checkAddress(String address) {
        return matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("address", address)
                .toString();
    }
}
