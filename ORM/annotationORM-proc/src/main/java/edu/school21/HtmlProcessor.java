package edu.school21;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

@SupportedAnnotationTypes({"edu.school21.HtmlForm", "edu.school21.HtmlInput"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedHtmlForm = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        Set<? extends Element> annotatedHtmlInput = roundEnv.getElementsAnnotatedWith(HtmlInput.class);

        Set<String> fileNames = new TreeSet<String>();
        for (Element elementHtmlForm : annotatedHtmlForm) {
            fileNames.add(elementHtmlForm.getAnnotation(HtmlForm.class).fileName());
        }

        TreeMap<String, StringBuilder> FilesTexts = new TreeMap<>();
        for (String filename : fileNames) {
            FilesTexts.put(filename, new StringBuilder());
        }

        for (Element elementHtmlForm : annotatedHtmlForm) {
            if (elementHtmlForm.getKind() == ElementKind.CLASS) {
                HtmlForm htmlForm = elementHtmlForm.getAnnotation(HtmlForm.class);
                FilesTexts.get(htmlForm.fileName()).setLength(0);
                FilesTexts.get(htmlForm.fileName()).append("<form action = \"").append(htmlForm.action()).append("\" method = \"")
                        .append(htmlForm.method()).append("\">\n");
            }
        }
            for (Element elementHtmlInput : annotatedHtmlInput) {
                if (elementHtmlInput.getKind() == ElementKind.FIELD) {
                    HtmlInput htmlInput = elementHtmlInput.getAnnotation(HtmlInput.class);
                    FilesTexts.get(htmlInput.thisClass() + ".html").append("\t<input type = \"").append(htmlInput.type()).append("\" name = \"")
                            .append(htmlInput.name()).append("\" placeholder = \"").append(htmlInput.placeholder()).append("\">\n");
                }
            }

        for (Map.Entry<String, StringBuilder> entery : FilesTexts.entrySet()) {
            entery.getValue().append("\t<input type = \"submit\" value = \"Send\">\n</form>");
            FileWriter writer;
            try {
                writer = new FileWriter("target/classes/" + entery.getKey());
                writer.write(entery.getValue().toString());
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

}