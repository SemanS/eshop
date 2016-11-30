package com.webinson.zuzka.bean;

import com.ocpsoft.pretty.PrettyContext;
import com.webinson.zuzka.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

import org.apache.commons.io.FilenameUtils;

/**
 * Created by Slavo on 11/17/2016.
 */
@Component
@ViewScoped
public class EditorController implements Serializable {

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private Part file;

    @Getter
    @Setter
   /* @ManagedProperty(value = "#{param.selectedCard}")*/
    private String selectedCard;

    @Getter
    @Setter
    /*@ManagedProperty(value = "#{param.selectedCategory}")*/
    private String selectedCategory;

    @Autowired
    ItemService itemService;

    @PostConstruct
    public void init() {

        //text = showText();
    }

    public String showText() {

        String path = PrettyContext.getCurrentInstance().getRequestURL().toURL();
        String segments[] = path.split("/");
        String resultUrl = segments[segments.length - 1];

        return itemService.getTextOfItemByUrl(resultUrl);
    }

    public void saveText() {

        System.out.println(selectedCard);
        String path = PrettyContext.getCurrentInstance().getRequestURL().toURL();
        String segments[] = path.split("/");
        String resultUrl = segments[segments.length - 1];

        itemService.saveItemByUrl(resultUrl, text);
    }


    public void uploadListener() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        String fileName = FilenameUtils.getName(file.getName());
        String fileNamePrefix = FilenameUtils.getBaseName(fileName) + "_";
        String fileNameSuffix = "." + "jpg";
        // String fileNameSuffix = "." + FilenameUtils.getExtension(fileName);

        File uploadFolder = new File("/var/project/images");

        try {
            File result = File.createTempFile(fileNamePrefix, fileNameSuffix, uploadFolder);

            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[1024];
            int bulk;

            InputStream inputStream = file.getInputStream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }

                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            String value = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get("editor_input");
            setText(value + "<img src=\"/images/" + result.getName() + "\" />");

            RequestContext.getCurrentInstance().update("editor_input");

            FacesMessage msg = new FacesMessage("Succesful", file.getName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage("The files were not uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }

    }
}
