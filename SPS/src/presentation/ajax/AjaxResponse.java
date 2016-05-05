/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.ajax;

import org.json.JSONArray;
import org.json.JSONObject;



/**
 *
 * @author Nirmalie
 */
public class AjaxResponse {

    public static final int TYPE_XML = 1;
    public static final int TYPE_JSON = 2;
    public static final int TYPE_TEXT = 3;
    public static final int TYPE_TEXT_HTML = 4;
    private int type;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private String xml;
    private String text;

    public AjaxResponse(int type, Object arg) {
        this.type = type;
        if (type == TYPE_XML) {
            this.xml = (String) arg;
        } else if (type == TYPE_JSON) {
            if (arg instanceof JSONObject) {
                this.jsonObject = (JSONObject) arg;
            } else if (arg instanceof JSONArray) {
                this.jsonArray = (JSONArray) arg;
            }
        } else if (type == TYPE_TEXT) {
            this.text = (String) arg;
        } else if (type == TYPE_TEXT_HTML) {
            if (arg instanceof JSONObject) {
                this.jsonObject = (JSONObject) arg;
            } else if (arg instanceof JSONArray) {
                this.jsonArray = (JSONArray) arg;
            }
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
