package com.yyds.billshare.Controller;
import com.yyds.billshare.Model.Form.UserEditInfoForm;
import com.yyds.billshare.Model.Form.UserSignupForm;
import com.yyds.billshare.Model.User;
import com.yyds.billshare.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true", allowedHeaders = "*")
public class UserController {

    // image save path
    @Value("${path.avatar}")
    private String avatarSavePath;
    private final UserRepository userJpaRepository;
    private final ControllerHelper controllerHelper;
    public UserController(UserRepository userJpaRepository, ControllerHelper controllerHelper) {
        this.userJpaRepository = userJpaRepository;
        this.controllerHelper = controllerHelper;
    }

    @PostMapping("/create_user")
    public String register (@Valid UserSignupForm form, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors().toString();
        }

        if(!form.getAvatar().isEmpty())
            controllerHelper.saveFile(avatarSavePath, form.getAvatar());
        userJpaRepository.save(new User(form));

        // TODO: should throw exception if the email has been used
        return "register successful";
    }


    @PostMapping("/edit_profile")
    public String editProfile (@Valid UserEditInfoForm form,
                               BindingResult bindingResult,
                               @RequestHeader(value = "Authorization") String jwtToken) throws IOException {

        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors().toString();
        }
        User user = controllerHelper.getUserFromJWT(jwtToken);
        user.editInfo(form);

        // delete old avatar
        try{
            File file = new File(avatarSavePath + File.separator + user.getAvatar());
            if(file.delete()){
                System.out.println(file.getName() + " old avatar has been deleted!");
            }else{
                System.out.println("failed to delete old avatar!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        if(form.getAvatar()!=null && !form.getAvatar().isEmpty())
            controllerHelper.saveFile(avatarSavePath, form.getAvatar());
        userJpaRepository.save(user);

        return "edit info successful";
    }




    // test
    @GetMapping("/shit")
    private String testCase(){
        return "welcome";
    }
}
