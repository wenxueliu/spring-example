package com.example.session.listener;

import com.example.session.entity.UserVo;
import org.springframework.context.event.EventListener;
import org.springframework.session.Session;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.stereotype.Component;

/**
 * @author liuwenxue
 * @date 2023-01-01
 */
@Component
public class SessionEventListener {
    private static final String CURRENT_USER = "currentUser";

    @EventListener
    public void sessionDelete(SessionDeletedEvent event) {
        Session session = event.getSession();
        UserVo userVo = session.getAttribute(CURRENT_USER);
        //System.out.println("invalid session's user:" + userVo.toString());
    }

    @EventListener
    public void sessionCreate(SessionCreatedEvent event) {
        Session session = event.getSession();
        UserVo userVo = session.getAttribute(CURRENT_USER);
        //System.out.println("create session's user:" + userVo.toString());
    }

    @EventListener
    public void sessionCreate(SessionExpiredEvent event) {
        Session session = event.getSession();
        UserVo userVo = session.getAttribute(CURRENT_USER);
        //System.out.println("expired session's user:" + userVo.toString());
    }
}
