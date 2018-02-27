package org.montclairrobotics.alloy.update;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface Update {
    int updateRate() default 1;
    int priority() default 0;
}
