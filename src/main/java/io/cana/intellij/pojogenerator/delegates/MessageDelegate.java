package io.cana.intellij.pojogenerator.delegates;

import com.intellij.notification.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import groovy.lang.Singleton;
import io.cana.intellij.pojogenerator.errors.RoboPluginException;

import javax.inject.Inject;

/**
 * Created by vadim on 24.09.16.
 */
@Singleton
public class MessageDelegate {
    private static final String GROUP_DISPLAY = "PojoGenerator";
    private static final String GROUP_DISPLAY_LOG = "PojoGenerator LOG";
    private static final NotificationGroup GROUP_DISPLAY_ID_INFO =
            new NotificationGroup(GROUP_DISPLAY, NotificationDisplayType.BALLOON, true);
    private static final NotificationGroup GROUP_DISPLAY_ID_LOG =
            new NotificationGroup(GROUP_DISPLAY_LOG, NotificationDisplayType.NONE, true);

    @Inject
    public MessageDelegate() {
    }

    public void onPluginExceptionHandled(RoboPluginException exception) {
        showMessage(exception.getMessage(), exception.getHeader());
    }

    public void logEventMessage(String message) {
        final Notification notification = GROUP_DISPLAY_ID_LOG
                .createNotification(message, NotificationType.INFORMATION);
        sendNotification(notification);
    }

    public void showSuccessMessage() {
        final Notification notification = GROUP_DISPLAY_ID_INFO
                .createNotification("Pojo generation: Success", NotificationType.INFORMATION);
        sendNotification(notification);
    }

    private void sendNotification(final Notification notification) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                Project[] projects = ProjectManager.getInstance().getOpenProjects();
                Notifications.Bus.notify(notification, projects[0]);
            }
        });
    }

    private void showMessage(String message, String header) {
        Messages.showDialog(message, header, new String[]{"OK"}, -1, null);
    }
}
