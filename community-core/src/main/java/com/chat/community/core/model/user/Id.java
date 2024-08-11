package com.chat.community.core.model.user;

import static com.google.common.base.Preconditions.checkArgument;

public record Id<R, V>(Class<R> reference, V value) {

    public Id(Class<R> reference, V value) {
        this.reference = reference;
        this.value = value;
    }

    public static <R, V> Id<R, V> of(Class<R> reference, V value) {
        checkArgument(reference != null, "reference must be provided");
        checkArgument(value != null, "value must be provided");

        return new Id<>(reference, value);
    }
}
