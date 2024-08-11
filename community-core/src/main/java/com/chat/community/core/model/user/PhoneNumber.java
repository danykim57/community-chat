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
public class PhoneNumber {

    private final String number;

    public PhoneNumber() {
        this.number = null;
    }

    public PhoneNumber(String number) {
        checkArgument(isNotEmpty(number), "Phone number cannot be empty");
        checkArgument(number.length() >= 10, "Phone number must be at least 10 characters" );
        checkArgument(checkNumber(number), "Phone number is not a valid phone number");
        this.number = number;
    }

    private static boolean checkNumber(String number) {
        return matches("^(?:\\+82)?(?:0[1-9][0-9]{1,2})?[1-9][0-9]{2,3}[0-9]{4}$", number);
    }

    @Override
    public boolean equals(Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber phoneNumber = (PhoneNumber) o;
        return Objects.equals(number, phoneNumber.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("number", number)
                .toString();
    }
}
