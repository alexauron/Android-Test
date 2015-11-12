package test.coding.fuse.presentation.dagger;

import java.lang.annotation.Retention;

import javax.inject.Scope;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Scoping activity life time for injected objects
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {}
