package ru.house.manager.controller;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.web.bind.annotation.PathVariable;
import ru.house.manager.EntityDB.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import static ru.house.manager.controller.PageController.*;

import ru.house.manager.serviceDB.*;
import ru.house.manager.Hash.*;

@Controller
public class HousePajesController {
    @RequestMapping(value = "/house-requests", method = RequestMethod.GET)
    public String getHouseRequestsPage() {
        return "houseRequests";
    }



    @RequestMapping(value="/house-requests-1", method=RequestMethod.POST)
    public String postbutton00 (@RequestParam(value="button0") String numb) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_context = 0;
        return "houseRequests";

    }


    @RequestMapping(value="/house-requests-2", method=RequestMethod.POST)
    public String postbutton11 (@RequestParam(value="button1") String numb) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_context = 1;
        return "houseRequests";
    }

    @RequestMapping(value = "/house-request/{id}", method = RequestMethod.GET)
    public String getChosenRequest1(@PathVariable int id) {
        request_id = id;
        return "houseRequests";
    }

    @RequestMapping(value="/house-requests/{id}/comments", method=RequestMethod.POST)
    public String postHouseRequestCommentPage(@PathVariable int id, @RequestParam(value = "commentsText") String commentText) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_id = id;
        Comments comment = new Comments();
        CommentsService commentsService = new CommentsService();
        String text = new String(commentText.getBytes("ISO-8859-1"), "UTF-8");
        comment.setApplicationId(id);
        comment.setText(text);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        comment.setDate((dateFormat.format( date )).toString());
        ManagersService managersService = new ManagersService();
        Managers manager = new Managers();
        manager = managersService.getByAccountId(client_account_id);
        comment.setCommentator(manager.getCompanyName());
        commentsService.add(comment);
        return "houseRequests";
    }

    @RequestMapping(value="/house-requests/{id}", method=RequestMethod.POST)
    public String postHouseRequestsPage(@PathVariable int id, @RequestParam(value = "commentsText") String commentText) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_id = id;
        ApplicationsService applicationsService2 = new ApplicationsService();
        Applications application = new Applications();
        application.setStatus(Applications.STATUS_CLOSE);
        application.setApplicationsId(id);
        applicationsService2.update(application);
        Comments comment = new Comments();
        CommentsService commentsService = new CommentsService();
        String text = new String(commentText.getBytes("ISO-8859-1"), "UTF-8");
        String text2 = "Ваша заявка была выполнена Управляющей компанией! \n" + text +"\nСпасибо за Ваше обращение.";
        comment.setApplicationId(id);
        comment.setText(text2);
        Date date = new Date();
        comment.setDate(date.toString());
        ManagersService managersService = new ManagersService();
        Managers manager = new Managers();
        manager = managersService.getByAccountId(client_account_id);
        comment.setCommentator(manager.getCompanyName());
        commentsService.add(comment);
        return "houseRequests";
    }
}
