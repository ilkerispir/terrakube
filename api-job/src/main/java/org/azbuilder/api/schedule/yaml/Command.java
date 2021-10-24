package org.azbuilder.api.schedule.yaml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Command {
    private String runtime;
    private String script;
    private int priority;
    private boolean before;
    private boolean after;
}
