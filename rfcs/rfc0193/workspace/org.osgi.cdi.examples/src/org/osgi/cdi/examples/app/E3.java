package org.osgi.cdi.examples.app;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Qualifier;

import org.osgi.cdi.examples.Config;
import org.osgi.cdi.examples.ServiceRanking;
import org.osgi.service.cdi.annotations.Configuration;
import org.osgi.service.cdi.annotations.Service;

/*
 * An application scoped bean which provides a singleton service.
 *
 * Part of the Application Component, with PIDS = $
 *
 * It declares properties visible to the bean when injected using
 * @Configuration AND as service properties due to use of @Service
 *
 * The values are aggregated with the following precedence:
 * 1. @Qualifiers are converted to properties using the rules defined by the converter spec
 * 2. @Properties
 * 3. because this bean is considered part of the "Application Component", the
 *    configuration admin objects available match the PIDs, and their order, as
 *    defined in the "osgi.cdi" requirement, all of which are optional.
 *
 * Graph:
 *
 *    @ApplicationScoped
 *    E2
 *      \
 *       @ApplicationScoped
 *       @Configuration
 *       Config
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Foos.class)
@interface Foo {
	String value();
}
@Retention(RetentionPolicy.RUNTIME)
@interface Foos {
	Foo[] value();
}

@ApplicationScoped
@Service
@Foo("bar")
@Foo("fum")
@ServiceRanking(200)
public class E3 {

	@Inject
	@Configuration
	Config config;

}
