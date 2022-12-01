package com.company.jmixpm.screen.projecttaskbrowser;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.Task;
import io.jmix.core.common.event.EventHub;
import io.jmix.core.common.event.Subscription;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.DialogMode;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.util.EventObject;
import java.util.function.Consumer;

@UiController("ProjectTaskBrowser")
@UiDescriptor("project-task-browser.xml")
public class ProjectTaskBrowser extends Screen {
    @Autowired
    private CollectionLoader<Task> tasksDl;

    protected EventHub eventHub = new EventHub();

    public ProjectTaskBrowser withProject(@Nullable Project project) {
        if (project != null) {
            tasksDl.setParameter("projectId", project.getId());
        } else {
            tasksDl.removeParameter("projectId");
        }

        tasksDl.load();

        eventHub.publish(MyScreenEvent.class, new MyScreenEvent(this));

        return this;
    }

    public Subscription addEventListener(Consumer<MyScreenEvent> listener) {
        return eventHub.subscribe(MyScreenEvent.class, listener);
    }

    protected class MyScreenEvent extends EventObject {

        public MyScreenEvent(Object source) {
            super(source);
        }
    }
}