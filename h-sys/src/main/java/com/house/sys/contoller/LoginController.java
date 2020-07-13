package com.house.sys.contoller;

import com.house.common.base.entity.AjaxResult;
import com.house.common.exception.ExceptionCast;
import com.house.common.model.response.CommonCode;
import com.house.common.model.response.ResponseResult;
import com.house.sys.service.CaptchaService;
import com.house.sys.service.HUserService;
import com.house.sys.service.IQinNiuCloudStorageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description 登录相关
 * @Author huangW
 * @Date 2020/5/1
 * @Version V1.0
 */
@RestController
public class LoginController {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private HUserService hUserService;

    @Autowired
    private IQinNiuCloudStorageService iQinNiuCloudStorageService;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) {
        response.setHeader("Cache-Control", "no-store, no-cache");

        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = captchaService.getCaptcha(uuid);

        try {
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
            IOUtils.closeQuietly(out);
        } catch (IOException e){
            ExceptionCast.cast(CommonCode.CODE_FAIL);
        }
    }

    /**
     * 手机登录
     * @param phone
     * @return
     */
    @PostMapping("/phoneLogin")
    public ResponseResult login(@RequestParam String phone){
        String token = hUserService.login(phone);
        return new ResponseResult(CommonCode.LOGIN,token);
    }

    @PostMapping("upload")
    public AjaxResult upload(MultipartFile file) throws Exception {
        return AjaxResult.success("操作成功",iQinNiuCloudStorageService.upload(file.getBytes(), file.getOriginalFilename()));
    }
}
