# java 开发中的一些小技巧

## Optional


## 请求参数验证
对于参数较多且校验情况复杂的情况，我们可以采用以下几种方法来使代码更加优雅：

- 使用 Bean Validation (JSR 380)
- 创建自定义验证注解
- 使用验证器模式
- 使用 Spring 的 Validator 接口
- 使用 AOP 进行参数验证

### 使用 Bean Validation (JSR 380)
这是最常用和最简洁的方法。你可以在实体类的字段上添加验证注解，然后在控制器中使用 @Valid 注解。

```java
public class UserDTO {
    @NotNull(message = "用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名长度必须在4-20之间")
    private String username;
    
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    // getters and setters
}
    
@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) {
        // 处理逻辑
    }
}
```

### 创建自定义验证注解
对于复杂的验证逻辑，你可以创建自定义注解。

```java
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {
    String message() default "用户名已存在";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return userRepository.findByUsername(username) == null;
    }
}
```

### 使用验证器模式
对于更复杂的验证逻辑，可以使用验证器模式。

```java
public interface UserValidator {
    void validate(UserDTO user) throws ValidationException;
}

@Component
public class UserNameValidator implements UserValidator {
    @Override
    public void validate(UserDTO user) throws ValidationException {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new ValidationException("用户名不能为空");
        }
        // 其他验证逻辑
    }
}

@Component
public class UserEmailValidator implements UserValidator {
    @Override
    public void validate(UserDTO user) throws ValidationException {
        if (user.getEmail() == null || !user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidationException("邮箱格式不正确");
        }
        // 其他验证逻辑
    }
}

@Service
public class UserService {
    @Autowired
    private List<UserValidator> validators;

    public void createUser(UserDTO user) throws ValidationException {
        for (UserValidator validator : validators) {
            validator.validate(user);
        }
        // 处理逻辑
    }
}
```

### 使用 Spring 的 Validator 接口
Spring 提供了 Validator 接口，你可以实现这个接口来进行验证。

```java
@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            errors.rejectValue("username", "username.empty", "用户名不能为空");
        }
        // 其他验证逻辑
    }
}

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserValidator userValidator;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            // 处理错误
        }
        // 处理逻辑
    }
}
```

### 使用 AOP 进行参数验证
你可以使用 AOP 来集中处理参数验证，使控制器代码更加简洁。

```java
@Aspect
@Component
public class ValidationAspect {
    @Before("execution(* com.example.controller.*.*(..))")
    public void validateParameters(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Parameter[] parameters = method.getParameters();
        Object[] args = joinPoint.getArgs();
        
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(Valid.class)) {
                Set<ConstraintViolation<Object>> violations = validator.validate(args[i]);
                if (!violations.isEmpty()) {
                    throw new ConstraintViolationException(violations);
                }
            }
        }
    }
}
```

这些方法可以单独使用，也可以组合使用，以达到最佳的代码组织和可维护性。选择哪种方法取决于你的具体需求和项目结构。通常，对于简单的验证，使用 Bean Validation 就足够了；对于更复杂的验证逻辑，可以考虑使用验证器模式或自定义注解。


## ref
- Optional https://www.bilibili.com/video/BV17jxCeGEtw





