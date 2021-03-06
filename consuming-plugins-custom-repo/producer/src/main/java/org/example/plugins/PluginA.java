/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.example.plugins;

import org.gradle.api.Project;
import org.gradle.api.Plugin;

/**
 * A simple 'hello world' plugin.
 */
public class PluginA implements Plugin<Project> {
    public void apply(Project project) {
        // Register a task
        project.getTasks().register("a", task -> {
            task.doLast(s -> System.out.println("Hello from plugin 'org.example.a'"));
        });
    }
}
