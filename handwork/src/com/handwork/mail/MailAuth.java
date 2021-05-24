package com.handwork.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator{
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {

        return new PasswordAuthentication("ambirion0302@gmail.com","dkdlvhsvmfh1!");
    }
}

