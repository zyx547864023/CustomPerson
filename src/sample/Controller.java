package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * 添加一个残骸区
 * 残骸区添加船
 * 人物编辑器
 */
public class Controller {
    @FXML
    VBox vBox;
    @FXML
    private SplitPane splitPane;
    @FXML
    SplitPane firstStar;
    @FXML
    Separator firstTop;
    @FXML
    Text firstStart;
    @FXML
    TextField personId;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField factionId;
    @FXML
    ComboBox personality;
    @FXML
    ComboBox gender;
    @FXML
    TextField level;
    @FXML
    TextField portraitSprite;
    @FXML
    ComboBox rankId;
    @FXML
    ComboBox postId;
    @FXML
    ComboBox tag;
    @FXML
    ComboBox importance;
    @FXML
    ComboBox officerOrAdmin;
    @FXML
    Button addSkill;
    @FXML
    Text firstEnd;
    @FXML
    Separator firstBottom;
    List<SplitPane> skillPaneList = new ArrayList<>();
    @FXML
    private SplitPane addSkill(ActionEvent event){
        int skillIndex = skillPaneList.size();
        SplitPane skillPane = new SplitPane();
        Separator top = new Separator();
        top.setStyle("-fx-background-color: BLUE;");
        skillPane.getItems().add(top);
        Text start = new Text();
        start.setText("技能"+skillIndex+"开始");
        skillPane.getItems().add(start);
        skillPane.setOrientation(Orientation.VERTICAL);
        ComboBox skill = new ComboBox();
        skill.setId("skill");
        skill.setPromptText("技能");
        skill.getItems().addAll(
                //# COMBAT
                "helmsmanship@操舵技术",
                "combat_endurance@战斗耐力",
                "impact_mitigation@冲击缓解",
                "damage_control@损伤管制",
                "field_modulation@相场调制",
                "point_defense@点防专精",
                "target_analysis@目标解析",
                "ballistic_mastery@实弹大师",
                "systems_expertise@系统专精",
                "missile_specialization@导弹特化",

//# LEADERSHIP
                "tactical_drills@战术演习",
                "coordinated_maneuvers@协调机动",
                "wolfpack_tactics@狼群战术",
                "crew_training@船员训练",
                "fighter_uplink@战机传讯",
                "carrier_group@航母集群",
                "officer_training@军官训练",
                "officer_management@军官管理",
                "best_of_the_best@万里挑一",
                "support_doctrine@辅助学说",
                "force_concentration@力量集中",

//# TECHNOLOGY
                "navigation@精准导航",
                "sensors@传感探测",
                "gunnery_implants@火控植入",
                "energy_weapon_mastery@能量大师",
                "electronic_warfare@电子对抗",
                "flux_regulation@幅能调节",
                "cybernetic_augmentation@模控增强",
                "phase_corps@相位调谐",
                "special_modifications@特殊改装",
                "neural_link@神经链接",
                "automated_ships@无人战舰",


//# INDUSTRY
                "bulk_transport@大宗物流",
                "salvaging@打捞作业",
                "field_repairs@现场维修",
                "ordnance_expert@军械专家",
                "polarized_armor@极化装甲",
                "containment_procedures@密闭流程",
                "makeshift_equipment@临时设备",
                "industrial_planning@工业规划",
                "derelict_contingent@废船舰队",
                "hull_restoration@船体修复",




//# Misc non-player
                "omega_ecm@欧米伽级电子战",
                "hypercognition@超越认知",





//# removed in 0.95.1a, but kept around for save compatibility
                "space_operations@太空行动",
                "planetary_operations@地表行动",
                "colony_management@殖民管理",
                "strike_commander@打击指挥",
                "ranged_specialization@远程特化",
                "shield_modulation@护盾调制",
                "phase_mastery@相位大师",
                "reliability_engineering@可靠工程",
                "weapon_drills@武装演习",
                "auxiliary_support@辅助支援",

                "aptitude_combat@战斗",
                "aptitude_leadership@领导",
                "aptitude_technology@技术",
                "aptitude_industry@建筑"
        );
        skill.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return skill.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        skillPane.getItems().add(skill);
        TextField level = new TextField();
        level.setId("level");
        level.setPromptText("技能等级");
        skillPane.getItems().add(level);
        Text end = new Text();
        end.setText("技能"+skillIndex+"结束");
        skillPane.getItems().add(end);
        Separator bottom = new Separator();
        bottom.setStyle("-fx-background-color: BLUE;");
        skillPane.getItems().add(bottom);
        splitPane.getItems().add(splitPane.getItems().indexOf(addSkill),skillPane);
        skillPaneList.add(skillPane);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("实在是不会写所以用alert刷新布局");
        alert.showAndWait();
        return skillPane;
    }
    @FXML
    private void initialize() {
        personality.getItems().addAll(
                "timid@胆小",
                "cautious@谨慎",
                "steady@镇定",
                "aggressive@激进",
                "reckless@鲁莽"
        );
        personality.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return personality.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        gender.getItems().addAll(
                "MALE@男",
                "FEMALE@女"
        );
        gender.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return gender.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        List<String> rankIdList = new ArrayList<>();
        String SPACE_SAILOR = "spaceSailor@太空水手";rankIdList.add(SPACE_SAILOR);
        String SPACE_CHIEF = "spaceChief@太空司令";rankIdList.add(SPACE_CHIEF);
        String SPACE_ENSIGN = "spaceEnsign@太空司令部";rankIdList.add(SPACE_ENSIGN);
        String SPACE_LIEUTENANT = "spaceLieutenant@太空中尉";rankIdList.add(SPACE_LIEUTENANT);
        String SPACE_COMMANDER = "spaceCommander@太空指挥官";rankIdList.add(SPACE_COMMANDER);
        String SPACE_CAPTAIN = "spaceCaptain@太空上尉";rankIdList.add(SPACE_CAPTAIN);
        String SPACE_ADMIRAL = "spaceAdmiral@太空上将";rankIdList.add(SPACE_ADMIRAL);
        String GROUND_PRIVATE = "groundPrivate@地面士兵";rankIdList.add(GROUND_PRIVATE);
        String GROUND_SERGEANT = "groundSergeant@地面军士";rankIdList.add(GROUND_SERGEANT);
        String GROUND_LIEUTENANT = "groundLieutenant@地面中尉";rankIdList.add(GROUND_LIEUTENANT);
        String GROUND_CAPTAIN = "groundCaptain@地面上尉";rankIdList.add(GROUND_CAPTAIN);
        String GROUND_MAJOR = "groundMajor@地面少校";rankIdList.add(GROUND_MAJOR);
        String GROUND_COLONEL = "groundColonel@地面上校";rankIdList.add(GROUND_COLONEL);
        String GROUND_GENERAL = "groundGeneral@地面将军";rankIdList.add(GROUND_GENERAL);
        String CITIZEN = "citizen@公民";rankIdList.add(CITIZEN);
        String UNKNOWN = "unknown@未知";rankIdList.add(UNKNOWN);
        String TERRORIST = "terrorist@恐怖";rankIdList.add(TERRORIST);
        String FREEDOM_FIGHTER = "freedomFighter@自由战斗机驾驶员";rankIdList.add(FREEDOM_FIGHTER);
        String CLONE = "clone@克隆人";rankIdList.add(CLONE);
        String BROTHER = "brother@兄弟";rankIdList.add(BROTHER);
        String FATHER = "father@父亲";rankIdList.add(FATHER);
        String SISTER = "sister@姐妹";rankIdList.add(SISTER);
        String MOTHER = "mother@母亲";rankIdList.add(MOTHER);
        String ELDER = "elder@长者";rankIdList.add(ELDER);
        String KNIGHT_CAPTAIN = "knightCaptain@骑士长";rankIdList.add(KNIGHT_CAPTAIN);
        String CHIEF_HIGH_INSPECTOR = "chiefHighInspector@最高领导人";rankIdList.add(CHIEF_HIGH_INSPECTOR);
        String DEPUTY_STAR_MARSHAL = "deputyStarMarshal@元帅";rankIdList.add(DEPUTY_STAR_MARSHAL);
        String GUARD_HIGH_DEPUTY_EXECUTOR = "guardHighDeputyExecutor@警卫高级副执行官";rankIdList.add(GUARD_HIGH_DEPUTY_EXECUTOR);
        String AGENT = "agent@探员";rankIdList.add(AGENT);
        String SPECIAL_AGENT = "specialAgent@特别探员";rankIdList.add(SPECIAL_AGENT);
        String PILOT = "pilot@驾驶员";rankIdList.add(PILOT);
        rankId.getItems().addAll(
                rankIdList
        );
        rankId.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return rankId.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        List<String> postIdList = new ArrayList<>();
        String POST_INTELLIGENCE_DIRECTOR = "intelligenceDirector@情报总监";
        postIdList.add(POST_INTELLIGENCE_DIRECTOR);
        String POST_GUARD_LEADER = "guardLeader@警卫队长";
        postIdList.add(POST_GUARD_LEADER);
        String POST_FACTION_LEADER = "factionLeader@派系领袖";
        postIdList.add(POST_FACTION_LEADER);
        String POST_PROVOST = "provost@教务长";
        postIdList.add(POST_PROVOST);
        String POST_ACADEMICIAN = "academician@学者";
        postIdList.add(POST_ACADEMICIAN);
        String POST_SCIENTIST = "scientist@科学家";
        postIdList.add(POST_SCIENTIST);
        String POST_AGENT = "agent@探员";
        postIdList.add(POST_AGENT);
        String POST_ARISTOCRAT = "aristocrat@贵族";
        postIdList.add(POST_ARISTOCRAT);
        String POST_SPECIAL_AGENT = "specialAgent@特别探员";
        postIdList.add(POST_SPECIAL_AGENT);
        String POST_GUILDMASTER = "guildMaster@帮会首领";postIdList.add(POST_GUILDMASTER);
        String POST_COMMUNE_LEADER = "communeLeader@行政区领导人";postIdList.add(POST_COMMUNE_LEADER);
        String POST_PATROL_COMMANDER = "patrolCommander@巡逻指挥官";postIdList.add(POST_PATROL_COMMANDER);
        String POST_FLEET_COMMANDER = "fleetCommander@舰队指挥官";postIdList.add(POST_FLEET_COMMANDER);
        String POST_BASE_COMMANDER = "baseCommander@基地指挥官";postIdList.add(POST_BASE_COMMANDER);
        String POST_OUTPOST_COMMANDER = "outpostCommander@前哨指挥官";postIdList.add(POST_OUTPOST_COMMANDER);
        String POST_PORTMASTER = "portmaster@港口长";postIdList.add(POST_PORTMASTER);
        String POST_STATION_COMMANDER = "stationCommander@空间站指挥官";postIdList.add(POST_STATION_COMMANDER);
        String POST_SUPPLY_OFFICER = "supplyOfficer@供应员";postIdList.add(POST_SUPPLY_OFFICER);
        String POST_SUPPLY_MANAGER = "supplyManager@供应经理";postIdList.add(POST_SUPPLY_MANAGER);
        String POST_MEDICAL_SUPPLIER = "medicalSupplier@医疗供应商";postIdList.add(POST_MEDICAL_SUPPLIER);
        String POST_CITIZEN = "citizen@公民";postIdList.add(POST_CITIZEN);
        String POST_OFFICER = "officer@官员";postIdList.add(POST_OFFICER);
        String POST_OFFICER_FOR_HIRE = "officer_for_hire@公务员";postIdList.add(POST_OFFICER_FOR_HIRE);
        String POST_MERCENARY = "mercenary@雇佣兵";postIdList.add(POST_MERCENARY);
        String POST_INVESTIGATOR = "investigator@调查员";postIdList.add(POST_INVESTIGATOR);
        String POST_ADMINISTRATOR = "administrator@管理员";postIdList.add(POST_ADMINISTRATOR);
        String POST_MILITARY_ADMINISTRATOR = "militaryAdministrator@军事行政长官";postIdList.add(POST_MILITARY_ADMINISTRATOR);
        String POST_FREELANCE_ADMIN = "freeAdmin@自由管理人";postIdList.add(POST_FREELANCE_ADMIN);
        String POST_GENERIC_MILITARY = "genericMilitary@通用军事";postIdList.add(POST_GENERIC_MILITARY);
        String POST_TRADER = "trader@交易者";postIdList.add(POST_TRADER);
        String POST_MERCHANT = "merchant@商人";postIdList.add(POST_MERCHANT);
        String POST_INVESTOR = "investor@投资者";postIdList.add(POST_INVESTOR);
        String POST_EXECUTIVE = "executive@总经理";postIdList.add(POST_EXECUTIVE);
        String POST_SENIOR_EXECUTIVE = "seniorExecutive@高级管理人员";postIdList.add(POST_SENIOR_EXECUTIVE);
        String POST_COMMODITIES_AGENT = "commodities_agent@商品代理商";postIdList.add(POST_COMMODITIES_AGENT);
        String POST_ENTREPRENEUR = "entrepreneur@企业家";postIdList.add(POST_ENTREPRENEUR);
        String POST_SHADY = "shady@可疑人员";postIdList.add(POST_SHADY);
        String POST_GANGSTER = "gangster@匪徒";postIdList.add(POST_GANGSTER);
        String POST_SMUGGLER = "smuggler@走私";postIdList.add(POST_SMUGGLER);
        String POST_FENCE = "fence@赃物销售";postIdList.add(POST_FENCE);
        String POST_CRIMINAL = "criminal@罪犯";postIdList.add(POST_CRIMINAL);
        String POST_MINORCRIMINAL = "minorCriminal@未成年罪犯";postIdList.add(POST_MINORCRIMINAL);
        String POST_ARMS_DEALER = "armsDealer@军火商";postIdList.add(POST_ARMS_DEALER);
        String POST_SPACER = "spacer@宇航员";postIdList.add(POST_SPACER);
        String POST_DOCTOR = "doctor@医生";postIdList.add(POST_DOCTOR);
        String POST_HACKER = "hacker@黑客";postIdList.add(POST_HACKER);
        String POST_HERMIT = "hermit@隐士";postIdList.add(POST_HERMIT);
        String POST_PENSIONER = "pensioner@退休";postIdList.add(POST_PENSIONER);
        String POST_ACTIVIST = "activist@激进分子";postIdList.add(POST_ACTIVIST);
        String POST_TERRORIST = "terrorist@恐怖主义者";postIdList.add(POST_TERRORIST);
        String POST_SYNOD_SUBCURATE = "synodSubcurate@副牧师";postIdList.add(POST_SYNOD_SUBCURATE);
        String POST_ARCHCURATE = "archcurate@大副牧师";postIdList.add(POST_ARCHCURATE);
        String POST_WARLORD = "warlord@军阀";postIdList.add(POST_WARLORD);
        String POST_UNKNOWN = "unknown@未知";postIdList.add(POST_UNKNOWN);
        String POST_SHRINE_PRIEST = "shrinePriest@神社牧师";postIdList.add(POST_SHRINE_PRIEST);
        String POST_EXCUBITOR_ORBIS = "excubitorOrbis@奥比斯开创者";postIdList.add(POST_EXCUBITOR_ORBIS);
        String POST_INITIATE = "initiate@新入会的人";postIdList.add(POST_INITIATE);
        String POST_NOVICE = "novice@新手";postIdList.add(POST_NOVICE);
        String POST_PILGRIM = "pilgrim@朝圣者";postIdList.add(POST_PILGRIM);
        postId.getItems().addAll(
                postIdList
        );
        postId.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return postId.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        //public static final String CONTACT_MILITARY = "military"; //军事
        //public static final String CONTACT_TRADE = "trade"; //贸易
        //public static final String CONTACT_UNDERWORLD = "underworld"; //黑社会
        //public static final String CONTACT_SCIENCE = "science"; //科学
        //public static final String CONTACT_PATHER = "pather"; //左径
        tag.getItems().addAll(
                "military@军事",
                "trade@贸易",
                "underworld@黑帮",
                "science@科学",
                "pather@左径"
        );
        tag.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return tag.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        importance.getItems().addAll(
                "VERY_LOW@非常低",
                "LOW@低",
                "MEDIUM@中",
                "HIGH@高",
                "VERY_HIGH@非常高"
        );
        importance.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return importance.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        officerOrAdmin.getItems().addAll(
                "舰船军官",
                "星球管理",
                "星球联系人"
        );
        officerOrAdmin.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return officerOrAdmin.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
    }

    //new
    @FXML
    protected void newConfig(ActionEvent event) {
        for (Node node:splitPane.getItems()) {
            if (node instanceof TextField) {
                TextField textField = (TextField)node;
                textField.setText("");
            }
            else if (node instanceof ComboBox){
                ComboBox comboBox = (ComboBox)node;
                comboBox.setValue("");
            }
            else if (node instanceof CheckBox){
                CheckBox checkBox = (CheckBox)node;
                checkBox.setSelected(false);
            }
        }
        for (SplitPane skillPane:skillPaneList) {
            splitPane.getItems().remove(skillPane);
        }
        skillPaneList.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("实在是不会写所以用alert刷新布局");
        alert.showAndWait();
    }

    @FXML
    protected void saveConfig(ActionEvent event) {
        saveConfig();
    }
    protected JSONObject saveConfig() {
        JSONObject personJSONObject = new JSONObject();
        personJSONObject.put("skill",new JSONArray());
        for (Node node:splitPane.getItems()) {
            if (node instanceof TextField) {
                TextField textField = (TextField)node;
                personJSONObject.put(textField.getId(),textField.getText());
            }
            else if (node instanceof ComboBox){
                ComboBox comboBox = (ComboBox)node;
                personJSONObject.put(comboBox.getId(),comboBox.getValue());
            }
            else if (node instanceof CheckBox){
                CheckBox checkBox = (CheckBox)node;
                personJSONObject.put(checkBox.getId(),checkBox.isSelected());
            }
            else if (node instanceof SplitPane) {
                SplitPane skillPane = (SplitPane)node;
                JSONObject skillJSONObject = new JSONObject();
                for (Node skillNode:skillPane.getItems()) {
                    if (skillNode instanceof TextField) {
                        TextField textField = (TextField)skillNode;
                        skillJSONObject.put(textField.getId(),textField.getText());
                    }
                    else if (skillNode instanceof ComboBox){
                        ComboBox comboBox = (ComboBox)skillNode;
                        skillJSONObject.put(comboBox.getId(),comboBox.getValue());
                    }
                    else if (skillNode instanceof CheckBox){
                        CheckBox checkBox = (CheckBox)skillNode;
                        skillJSONObject.put(checkBox.getId(),checkBox.isSelected());
                    }
                }
                JSONArray skillJSONArray = personJSONObject.getJSONArray("skill");
                skillJSONArray.put(skillJSONObject);
                personJSONObject.put("skill",skillJSONArray);
            }
        }

        String currentDirectory = System.getProperty("user.dir");
        System.out.println("当前程序所在目录：" + currentDirectory);
        FileWriter fr;
        String fileName = personId.getText()+System.currentTimeMillis()+".json";
        try {
            fr = new FileWriter(fileName);
            fr.write("");
            fr.flush();
            fr.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            Files.write(FileSystems.getDefault().getPath(fileName),
                    personJSONObject.toString().getBytes(Charset.forName("utf-8")), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(personJSONObject.toString());
        System.out.println(personJSONObject.toString());
        alert.showAndWait();
        return personJSONObject;
    }
    @FXML
    protected void generateCode(ActionEvent event) {
        saveConfig();
    }

    @FXML
    protected void openConfig(ActionEvent event) {
        newConfig(event);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开配置文件");

        // 显示文件选择对话框
        File file = fileChooser.showOpenDialog(vBox.getScene().getWindow());
        if (file != null) {
            try {
                // 读取文件内容
                byte[] fileContent = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
                // 处理文件内容
                System.out.println(new String(fileContent));
                try {
                    JSONObject personJSONObject = new JSONObject(new String(fileContent));
                    //星系
                    if (!personJSONObject.isNull("skill")){
                        JSONArray skillJSONArray = personJSONObject.getJSONArray("skill");
                        for (int index =0;index<skillJSONArray.length();index++) {
                            SplitPane skillPane = addSkill(null);
                            //赋值
                            JSONObject skillJSONObect = skillJSONArray.getJSONObject(index);
                            setValue(skillPane,skillJSONObect);
                        }
                    }
                    setValue(splitPane,personJSONObject);
                }
                catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setValue(SplitPane industry, JSONObject jsonObject) {
        for (Node node:industry.getItems()) {
            if (!jsonObject.isNull(node.getId())) {
                if (node instanceof TextField) {
                    TextField textField = (TextField) node;
                    textField.setText(jsonObject.getString(node.getId()));
                } else if (node instanceof ComboBox) {
                    ComboBox comboBox = (ComboBox) node;
                    comboBox.setValue(jsonObject.getString(node.getId()));
                } else if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    checkBox.setSelected(jsonObject.getBoolean(node.getId()));
                }
            }
        }
    }
}
