package be.kdg.Controller;

import be.kdg.Model.User;
import be.kdg.Service.UserService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Author: Ben Oeyen
 * Date: 7/02/13
 * Class: User REST Controller
 * Description:  Controller to handle REST service calls
 */

public class RestUserController {
    private static final Logger logger= Logger.getLogger(RestUserController.class);

    @RequestMapping(value = "/rest/users/{userId}", method = RequestMethod.GET)
    public void getFund(@PathVariable("userId") String userId) {
        User user = null;

        // validate input
        if (userId.isEmpty() || userId.length() < 5) {
            String sMessage = "Error invoking getFund - Invalid fund Id parameter";
           // return createErrorResponse(sMessage);
        }

        try {
            user = UserService.getUserById(Long.parseLong(userId));
        } catch (Exception e) {
            String sMessage = "Error invoking getFund. [%1$s]";
            //return createErrorResponse(String.format(sMessage, e.toString()));
        }

        logger.debug("Returning User: " + user.toString());
    }
}
