package com.company.jmixpm.screen.project;

import com.company.jmixpm.screen.projecttaskbrowser.ProjectTaskBrowser;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Project.browse")
@UiDescriptor("project-browse.xml")
@LookupComponent("projectsTable")
public class ProjectBrowse extends StandardLookup<Project> {

    @Autowired
    private ScreenBuilders screenBuilders;

    @Autowired
    private GroupTable<Project> projectsTable;

    @Subscribe("projectsTable.showTasks")
    public void onProjectsTableShowTasks(Action.ActionPerformedEvent event) {
        Project selected = projectsTable.getSingleSelected();
        if (selected == null) {
            return;
        }

        screenBuilders.screen(this)
                .withScreenClass(ProjectTaskBrowser.class)
                .build()
                .withProject(selected)
                .show();
    }
}