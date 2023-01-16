module application {
    uses jmp.bank.api.Bank;
    uses jmp.service.api.Service;
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;
    requires jmp.dto;
    requires jmp.bank.api;


}