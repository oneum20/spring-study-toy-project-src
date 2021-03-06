package com.oneum20.study.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignUpForm signUpForm = (SignUpForm)o;
        if(accountRepository.existsByEmail(signUpForm.getEmail())){
            errors.rejectValue("email", "invalid.email", new Object[]{signUpForm.getEmail()}, "이미 사용중인 이메일 입니다.");
        }

        if(accountRepository.existsByNickname(signUpForm.getEmail())){
            errors.rejectValue("nickname", "invalid.nickname", new Object[]{signUpForm.getEmail()}, "이미 사용중인 닉네임 입니다.");
        }
    }
}
