# SE4367

To run this repository please first clone the Pitest-master repository by Henry Cole. Replace Mutator.java with the current version in our repository. In addition, put all of the mutators in the Mutators folder.

In the terminal please run from the Pitest-master folder:
mvn clean
mvn install -DskipTests

Please add this to the test pom.xml. Additionaly, please ensure the versions match Henry Cole's pom.xml files.

    <build>
        <plugins>
            <plugin>
                <groupId>org.pitest</groupId>
                <artifactId>pitest-maven</artifactId>
                <version>1.4.4-SNAPSHOT</version>
                <configuration>
                    <mutators>
                        <mutator>ROR1</mutator>
                        <mutator>ROR2</mutator>
                        <mutator>ROR3</mutator>
                        <mutator>ROR4</mutator>
                        <mutator>ROR5</mutator>
                        <mutator>AOR1</mutator>
                        <mutator>AOR2</mutator>
                        <mutator>AOR3</mutator>
                        <mutator>AOR4</mutator>
                    </mutators>
                </configuration>
            </plugin>
        </plugins>
    </build>

Finally, our mutators can be run with this command from the test folder:

mvn org.pitest:pitest-maven:mutationCoverage

Edited 12/6/2018

Adding new files to mutate groovy
-MathMutatorGroovy is the default MathMutator for groovy code

