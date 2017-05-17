package com.cstructor.android310;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "Chat")
public class ChatMapper {
    private String chatId;
    private String userName;
    private String text;

    @DynamoDBHashKey(attributeName = "ChatId")
    public String getChatId(){
        return chatId;
    }

    public void setChatId(String chatId){
        this.chatId = chatId;
    }

    @DynamoDBAttribute
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    @DynamoDBAttribute
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }
}
