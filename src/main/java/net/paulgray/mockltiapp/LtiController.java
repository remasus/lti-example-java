/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockltiapp;

import javax.servlet.http.HttpServletRequest;

import org.imsglobal.basiclti.LtiVerificationResult;
import org.imsglobal.aspect.LtiLaunch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pgray
 */
@Controller
public class LtiController {
    
    @LtiLaunch
    @RequestMapping(value = {"/lti", "/test"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String ltiEntry(HttpServletRequest request, LtiVerificationResult result){
        
        if(!result.getSuccess()){
            System.out.println("********************got error: " + result.getError() + " - " + result.getMessage());
        } else {
            System.out.println("got user: " + result.getLtiLaunchResult().getUser().getId());
        }
        return "lti";
    }
    
    @LtiLaunch(rejectIfBad = false, keyService = "keyService")
    @RequestMapping(value = {"/another"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String ltiTest(HttpServletRequest request, ModelMap map, LtiVerificationResult result){
        System.out.println("********************got another result:" + result.toString());
        return "lti";
    }
    
}
