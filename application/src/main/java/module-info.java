module application {
    requires jmp.dto;
    requires jmp.bank.api;
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;
    requires jmp.service.api;
    requires static lombok;
    requires org.slf4j;
}