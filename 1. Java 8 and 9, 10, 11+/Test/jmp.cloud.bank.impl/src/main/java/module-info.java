module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;
    exports jmp.cloud.bank.impl;
    provides jmp.bank.api.Bank
            with jmp.cloud.bank.impl.Kaspi;
}