package com.melogtm.cli.enums;

public enum TaskOperations {
    ADD, UPDATE, DELETE, MARK_IN_PROGRESS, MARK_DONE, LIST;

    public enum ListFlags {
        DONE, TO_DO, IN_PROGRESS
    }
}
