package thanhtuu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by anh.dang on 2/4/2017.
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("error-page");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Lỗi: Kết nối";
                break;
            }
            case 401: {
                errorMsg = "Lỗi: Bạn không được phép";
                break;
            }
            case 403: {
                errorMsg = "Lỗi: Bạn không có quyền truy cập vào trang này";
                break;
            }
            case 404: {
                errorMsg = "Lỗi: Không tìm thấy trang";
                break;
            }
            case 500: {
                errorMsg = "Lỗi: Server lỗi";
                break;
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
