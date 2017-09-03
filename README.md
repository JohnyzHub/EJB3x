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
            @PostConstruct – Invokes the method immediately after the bean creation.
            @PreDestroy – Invokes the method immediately before the container destroys the bean.
            @Startup – Eagerly initializes bean instance
            @DependsOn – States that the current bean is dependent on some other bean
            
            @ConcurrencyManagement - 
              Container Managed Concurrency(default) – Container controls the concurrent access
                @Lock(LockType.READ/WRITE) – Specifies how the container must manage 
                                             the concurrency when client invokes a method call.
                                             Lock can be applied at method level and or class level.
              Bean Managed Concurrency – 
                              Bean is responsible to control concurrent access to methods.
                       @AccessTimeout(value, unit) – Duration that access attempt should be blocked before time out.
                                        Value = 0: Concurrent access not permitted.
                                                -1: Request will be blocked indefinitely until gets access.
                                                >0: Time out value 
                                                       Unit of type of java.util.concurrent.TimeUnit


            @Stateful(passivationCapable=false) – Deactivates passivation behaviour. It is true by default.
            @PrePassivate – Invokes the method before stateful session bean is passivated.
            @PostAcitvate – Invokes the method after the stateful session bean made active 
                              from passivation.
            @StatefulTimeout – Duration a stateful session is permitted to be remain idle. 
                                    The value and unit description is same as explained for @AccessTimeout.
            @Remove – Removes bean instance permanently from memory.
            
            *SessionContext – Gives programatic access to runtime context 
                    provided by session bean instance
            
            @Asynchronous – Marks a session bean method as an asynchronous method.
                              Annotated at class level, marks all the methods asynchronous. 
                              These methods must have return types either void or Future<v>.
                                        v- is the result type.
            


            Deployment descriptor (ejb-jar.xml) overrides the annotations during deployment, if one exist.
                    This is to minimize code touch for property value changes.


            Inject session beans using @EJB or @Inject?
                        As a thumb rule, use @Inject for local and no-interface session beans 
                          and @EJB to inject remote session beans.
                        Check this blog: 
                                        http://www.adam-bien.com/roller/abien/entry/inject_vs_ejb
                        and page 534 of JEE7 DHandbook
                        and page 260 of BJEE7.

            
            Timers can be created in two ways.
              Declarative way: 
                  @Schedule, @Schedules or deployment descriptor
                            Eg: This creates two timers one for every two hours and another every wed 2pm
                              @Schedules({
                                        @Schedule(hour="2"),
                                        @Schedule(hour="14", dayOfWeek="Wed")
                                        })
              Programatic way: 
                  TimerService interface with ScheduleExpression, to create the timer
                  @Timeout on callback method, to invoke the timer
                            Eg:
                              @Resource
                              TimerService timerService
                              timerService.createCalenderTimer(new scheduleExpression(), ….)
                              @Timeout
                              public void sendNotification(Timer timer){...}


                    Session beans are implicitly transactional.

            Transaction types:
                Resource_Local (Bean Managed Transaction)
                JTA (Container Managed Transaction)
                BMP vs CMP: http://tomee.apache.org/jpa-concepts.html
              @PersistanceContext
              @DatasourceDefinition	

          CMT Transaction Attributes:
                @TransactionAttribute -- Applies to method and/or class 
                    
                    Required -          Business method will be executed inside a transaction context. 
                                        If the transaction not available a new transaction will be created.
                    
                    Mandatory -         Business method should be executed inside a transaction context. 
                                        If the transaction does not available an <b>exception</b> will be raised.
                    
                    Requires_New -      Business method will always be executed inside a new transaction.
                                        The current transaction (if exist) will be temporarily suspended 
                                        before the method invocation, and resumes after the method completion.
 
                    Supports -          Method execution should be inside the client's transaction context,
                                        Method will be executed without a transaction context if no transaction 
                                        available.
                                        
                    Not_Supported -     The method execution will always be outside transaction context, irrespective
                                        of the existance of the transaction.
                    
                    Never  -            The method execution must not be performed inside a transaction context.
                                        An <b>exception</b> will be raised if invoked inside a transaction.
                                        
                    Check the page#297 of BJEE7 for tabular form.

          Exception Handling:
                    SystemException:              Subclass of RuntimeException are knows as SystemException.
                                                  EJB Container performs rollback automatically for these exception.
                    
                    ApplicationException:         A subclass of checked or unchecked exception that is annotated with
                                                  @ApplicationException or xml equivalent in deployment descriptor file.
                                                  Programmer defines the rollback strategy for these exceptions.
                                                        rollback- true or false
                     Check the page#300 of BJEE7 tabular form for better understanding.
