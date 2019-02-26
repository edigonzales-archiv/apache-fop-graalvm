# apache-fop-graalvm

```
./gradlew build && unzip -o build/distributions/apache-fop-graalvm.zip -d build/distributions/apache-fop-graalvm

./apache-fop-graalvm/apache-fop-graalvm/bin/apache-fop-graalvm --xml /Users/stefan/sources/apache-fop-graalvm/src/test/data/projectteam.xml --out /Users/stefan/tmp/
```

```
$GRAALVM_HOME/bin/native-image --verbose --allow-incomplete-classpath -H:+ReportExceptionStackTraces -H:-UseServiceLoaderFeature -H:IncludeResources='.*/*.xsl$'  -H:Log=registerResource --no-server -cp "apache-fop-graalvm/apache-fop-graalvm/lib/*" apache.fop.graalvm.App

./apache.fop.graalvm.app --xml /Users/stefan/sources/apache-fop-graalvm/src/test/data/projectteam.xml --out /Users/stefan/tmp/
```


Runtime error V1:
```
java.lang.UnsupportedOperationException: Don't know how to handle "application/pdf" as an output format. Neither an FOEventHandler, nor a Renderer could be found for this output format.
	at org.apache.fop.render.RendererFactory.createFOEventHandler(RendererFactory.java:365)
	at org.apache.fop.fo.FOTreeBuilder.<init>(FOTreeBuilder.java:107)
	at org.apache.fop.apps.Fop.createDefaultHandler(Fop.java:104)
	at org.apache.fop.apps.Fop.<init>(Fop.java:78)
	at org.apache.fop.apps.FOUserAgent.newFop(FOUserAgent.java:182)
	at org.apache.fop.apps.FopFactory.newFop(FopFactory.java:239)
	at apache.fop.graalvm.Converter.runXml2Pdf(Converter.java:51)
	at apache.fop.graalvm.App.main(App.java:42)
```

Build error V2:
```
Warning: class initialization of class org.apache.batik.bridge.RhinoInterpreter failed with exception java.lang.NoClassDefFoundError: org/mozilla/javascript/WrapFactory. This class will be initialized at run time because either option --report-unsupported-elements-at-runtime or option --allow-incomplete-classpath is used for image building. Use the option --delay-class-initialization-to-runtime=org.apache.batik.bridge.RhinoInterpreter to explicitly request delayed initialization of this class.
[thread:31] scope: ForkJoinPool-3-worker-1
  [thread:31] scope: ForkJoinPool-3-worker-1.ClosedWorldAnalysis
  Context: StructuredGraph:26272{AnalysisMethod<URLClassPath$3.run -> HotSpotMethod<URLClassPath$3.run()>>}
  Context: AnalysisMethod<URLClassPath$3.run -> HotSpotMethod<URLClassPath$3.run()>>
    [thread:31] scope: ForkJoinPool-3-worker-1.ClosedWorldAnalysis.AnalysisGraphBuilderPhase
    Exception raised in scope ForkJoinPool-3-worker-1.ClosedWorldAnalysis.AnalysisGraphBuilderPhase: org.graalvm.compiler.java.BytecodeParser$BytecodeParserError: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: Unsupported type sun.misc.URLClassPath$JarLoader is reachable
    To diagnose the issue, you can add the option --report-unsupported-elements-at-runtime. The unsupported element is then reported at run time when it is accessed the first time.
    	at parsing sun.misc.URLClassPath$3.run(URLClassPath.java:575)
    	at org.graalvm.compiler.java.BytecodeParser.throwParserError(BytecodeParser.java:2435)
    	at com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.throwParserError(SharedGraphBuilderPhase.java:88)
    	at org.graalvm.compiler.java.BytecodeParser.iterateBytecodesForBlock(BytecodeParser.java:3189)
    	at org.graalvm.compiler.java.BytecodeParser.processBlock(BytecodeParser.java:2993)
    	at org.graalvm.compiler.java.BytecodeParser.build(BytecodeParser.java:890)
    	at org.graalvm.compiler.java.BytecodeParser.buildRootMethod(BytecodeParser.java:784)
    	at org.graalvm.compiler.java.GraphBuilderPhase$Instance.run(GraphBuilderPhase.java:95)
    	at org.graalvm.compiler.phases.Phase.run(Phase.java:49)
    	at org.graalvm.compiler.phases.BasePhase.apply(BasePhase.java:197)
    	at org.graalvm.compiler.phases.Phase.apply(Phase.java:42)
    	at org.graalvm.compiler.phases.Phase.apply(Phase.java:38)
    	at com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.parse(MethodTypeFlowBuilder.java:205)
    	at com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.apply(MethodTypeFlowBuilder.java:324)
    	at com.oracle.graal.pointsto.flow.MethodTypeFlow.doParse(MethodTypeFlow.java:310)
    	at com.oracle.graal.pointsto.flow.MethodTypeFlow.ensureParsed(MethodTypeFlow.java:300)
    	at com.oracle.graal.pointsto.flow.MethodTypeFlow.addContext(MethodTypeFlow.java:107)
    	at com.oracle.graal.pointsto.DefaultAnalysisPolicy$DefaultVirtualInvokeTypeFlow.onObservedUpdate(DefaultAnalysisPolicy.java:186)
    	at com.oracle.graal.pointsto.flow.TypeFlow.notifyObservers(TypeFlow.java:347)
    	at com.oracle.graal.pointsto.flow.TypeFlow.update(TypeFlow.java:389)
    	at com.oracle.graal.pointsto.BigBang$2.run(BigBang.java:508)
    	at com.oracle.graal.pointsto.util.CompletionExecutor.lambda$execute$0(CompletionExecutor.java:174)
    	at java.util.concurrent.ForkJoinTask$RunnableExecuteAction.exec(ForkJoinTask.java:1402)
    	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
    	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
    	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
    	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
    Caused by: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: Unsupported type sun.misc.URLClassPath$JarLoader is reachable
    To diagnose the issue, you can add the option --report-unsupported-elements-at-runtime. The unsupported element is then reported at run time when it is accessed the first time.
    	at com.oracle.svm.hosted.substitute.AnnotationSubstitutionProcessor.lookup(AnnotationSubstitutionProcessor.java:114)
    	at com.oracle.graal.pointsto.infrastructure.SubstitutionProcessor$ChainedSubstitutionProcessor.lookup(SubstitutionProcessor.java:113)
    	at com.oracle.graal.pointsto.infrastructure.SubstitutionProcessor$ChainedSubstitutionProcessor.lookup(SubstitutionProcessor.java:113)
    	at com.oracle.graal.pointsto.meta.AnalysisUniverse.lookupAllowUnresolved(AnalysisUniverse.java:199)
    	at com.oracle.graal.pointsto.infrastructure.WrappedConstantPool.lookupType(WrappedConstantPool.java:154)
    	at org.graalvm.compiler.java.BytecodeParser.lookupType(BytecodeParser.java:3992)
    	at org.graalvm.compiler.java.BytecodeParser.genNewInstance(BytecodeParser.java:4235)
    	at org.graalvm.compiler.java.BytecodeParser.processBytecode(BytecodeParser.java:5023)
    	at org.graalvm.compiler.java.BytecodeParser.iterateBytecodesForBlock(BytecodeParser.java:3184)
    	... 23 more

    Context obj com.oracle.svm.hosted.phases.AnalysisGraphBuilderPhase@26317e56
    Context obj StructuredGraph:26272{AnalysisMethod<URLClassPath$3.run -> HotSpotMethod<URLClassPath$3.run()>>}
    Context obj AnalysisMethod<URLClassPath$3.run -> HotSpotMethod<URLClassPath$3.run()>>
    Context obj com.oracle.svm.hosted.analysis.flow.SVMMethodTypeFlowBuilder@18fd1370
```

```
$GRAALVM_HOME/bin/native-image --verbose --allow-incomplete-classpath  --report-unsupported-elements-at-runtime --delay-class-initialization-to-runtime=org.apache.batik.bridge.RhinoInterpreter -H:+ReportExceptionStackTraces -H:-UseServiceLoaderFeature -H:IncludeResources='.*/*.xsl$' -H:Log=registerResource --no-server -cp "apache-fop-graalvm/apache-fop-graalvm/lib/*" apache.fop.graalvm.App

```



