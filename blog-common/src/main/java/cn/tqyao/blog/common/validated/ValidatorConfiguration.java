/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.common.validated;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/12 11:42 <br>
 */
@Configuration
public class ValidatorConfiguration {

    /**
     * @Valid  快速失败返回模式(只要有一个验证失败，则返回)
     * 两种验证模式配置方式之一：
     * ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
     *         .configure()
     *         .failFast( true )
     *         .buildValidatorFactory();
     * Validator validator = validatorFactory.getValidator();
     *
     * ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
     *           .configure()
     *           .addProperty( "hibernate.validator.fail_fast", "true" )
     *           .buildValidatorFactory();
     *
     *   return validatorFactory.getValidator();
     * @return
     */
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast(false)
                .buildValidatorFactory();

        return validatorFactory.getValidator();
    }

    /**
     * @Validated 快速失败返回模式(只要有一个验证失败，则返回)
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        //设置validator模式为快速失败返回
        postProcessor.setValidator(validator());
        return postProcessor;
    }
}
