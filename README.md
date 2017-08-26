          # EJB3x


          Session Bean Types:
                  Stateless
                  Stateful
                  Singleton

            JNDI Convension : 
                java:<scope>[/app]/module/bean[!<fully-qualified-interface-name>]

                  Scope : 	
                    global, app, module, comp

                            *Not an Annotation

            @Remote – Remote business interface
            @Local – Local business interface
            @LocalBean – No interface view
            @EJB – Injects reference of session bean.
            @Startup – Eagerly initializes bean instance
            @DependsOn – States that the current bean is dependent on some other bean
            @ConcurrencyManagement - 
              Container Managed Concurrency(default) – Container controls the concurrent access
                @Lock(LockType.READ/WRITE) – Specifies how the container must manage 								the concurrency when client invokes a method call.
                    Lock can be applied at method level and or class level.
              Bean Managed Concurrency – Bean is responsible to control concurrent 
                      access to methods.
                                   @AccessTimeout(value, unit) – Duration that access attempt should be blocked before 								time out.		
                  Value = 0: Concurrent access not permitted.
                               -1: Request will be blocked indefinitely until gets access.
                       >0: Time out value 
                       Unit of type of java.util.concurrent.TimeUnit



            @StatefulTimeout – Duration a stateful session is permitted to be remain idle. 
                The value and unit description is same as explained for @AccessTimeout.
            @Remove – Removes bean instance permanently from memory.
                    *SessionContext – Gives programatic access to runtime context 
                    provided by session bean instance
            @Asynchronous – Marks a session bean method as an asynchronous method.
                Annotated at class level, marks all the methods asynchronous. These methods 				must have return types either void or Future<v>.
                  v- is the result value type.
            @Stateful(passivationCapable=false) – Deactivates passivation behaviour. It is true by default.



            @PostConstruct – Invokes the method immediately after the bean creation.
            @PrePassivate – Invokes the method before stateful session bean is passivated.
            @PostAcitvate – Invokes the method after the stateful session bean made active 
                  from passivation.
            @PreDestroy – Invokes the method immediately before the container destroys the bean.


            Deployment descriptor (ejb-jar.xml) overrides the annotations during deployment, if one exist. 			This is to minimize code touch for property value changes.


            Inject session beans using @EJB or @Inject?
              As a thumb rule, use @Inject for local and no-interface session beans 
                and @EJB to inject remote session beans.
              Check this blog: 
                              http://www.adam-bien.com/roller/abien/entry/inject_vs_ejb
              and page 534 of Java EE 7 Developer Handbook
              and page 260 of Beginning java EE 7.

            Timers can be created in two ways.
              Declarative way: 
                  @Schedule, @Schedules or deployment descriptor
              Programatic way: 
                  TimerService interface with ScheduleExpression, to create the timer
                  @Timeout on callback method, to invoke the timer

                  Eg:
                    @Resource
                    TimerService timerService
                    timerService.createCalenderTimer(new scheduleExpression(), ….)

            Session beans are implicitly transactional.

            Persistence types:
                Resource_Local (Bean Managed Persistance)
                JTA (Container Managed Persistence)
                BMP vs CMP: 
                              http://tomee.apache.org/jpa-concepts.html
              @PersistanceContext
              @DatasourceDefinition	

