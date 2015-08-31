package com.co.colector.helpers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.co.colector.ColectorApplication;
import com.co.colector.database.SQLiteDB;
import com.co.colector.database.catalogo;
import com.co.colector.database.empresa;
import com.co.colector.database.entrada;
import com.co.colector.database.registro_form;
import com.co.colector.database.registros;
import com.co.colector.database.tab;
import com.co.colector.model.Catalog;
import com.co.colector.model.Enterprise;
import com.co.colector.model.Entry;
import com.co.colector.model.Registry;
import com.co.colector.model.Tab;

import java.util.ArrayList;

/**
 * Created by User on 26/08/2015.
 */
public class DatabaseHelper {

    private static SQLiteDB sqLiteDB;
    private static SQLiteDatabase sqLiteDatabase;
    private static registros registrosTable;
    private static empresa empresaTable;
    private static catalogo catalogoTable;
    private static entrada entradaTable;
    private static registro_form registroFormTable;
    private static tab tabTable;
    private static Cursor cursor;
    private static ArrayList<Registry> registryArrayList;

    private static void initializeDatabase(){
        sqLiteDB = new SQLiteDB(ColectorApplication.getInstance());
    }

    private static void initializeReadableDatabase(){
        sqLiteDatabase = sqLiteDB.getReadableDatabase();
    }

    private static void initializeWriteableDatabase(){
        sqLiteDatabase = sqLiteDB.getReadableDatabase();
    }

    public static void insertEmpresa(Enterprise enterprise){
        initializeDatabase();
        initializeWriteableDatabase();
        empresaTable = new empresa();
        empresaTable.insert(sqLiteDatabase,enterprise.getName(),enterprise.getSystem_id(), enterprise.getDescriptionEnterprise(), enterprise.getDb_system());
    }

    public static void insertTab(Tab tab){
        initializeDatabase();
        initializeWriteableDatabase();
        tabTable = new tab();
        tabTable.insert(sqLiteDatabase,tab.getTabId(), tab.getCatalogId());
    }

    public static void insertCatalogo(Catalog catalog){
        initializeDatabase();
        initializeWriteableDatabase();
        catalogoTable = new catalogo();
        catalogoTable.insert(sqLiteDatabase,catalog.getCatalogTitle(),catalog.getCatalogId(), catalog.getCatalogDescription(), catalog.getGrupoEntrada());
    }

    public static void insertEntry(Entry entry){
        initializeDatabase();
        initializeWriteableDatabase();
        entradaTable = new entrada();
        entradaTable.insert(sqLiteDatabase,entry.getEntryId(),entry.getTabId());
    }

    public static void insertRegistro(String catalogoId, String dbSistema, String sistemaId,
                                      String tabletId, String grupoEntrada, String tabId, String respuesta, String usuarioId, String entradaId, String directory_photos, String registroFormId) {
        initializeDatabase();
        initializeWriteableDatabase();
        registrosTable = new registros();
        registrosTable.insert(sqLiteDatabase,catalogoId,dbSistema,sistemaId,
                tabletId,grupoEntrada,tabId,respuesta,usuarioId,entradaId,directory_photos, registroFormId);
    }

    public static void insertRegistroForm() {
        initializeDatabase();
        initializeWriteableDatabase();
        registroFormTable = new registro_form();
        registroFormTable.insert(sqLiteDatabase);
    }

    public static void updateRegistro(String id, String answer){
        initializeDatabase();
        initializeWriteableDatabase();
        registrosTable = new registros();
        registrosTable.updateAnswer(sqLiteDatabase, id, answer);
    }

    public static ArrayList<Registry> getRegistrysOfForms(){
        initializeDatabase();
        initializeReadableDatabase();
        registrosTable = new registros();

        cursor = registrosTable.consulta(sqLiteDatabase);
        registryArrayList = new ArrayList<Registry>();

       /* if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()){
                registryArrayList.add(new Registry(String.valueOf(cursor.getInt(cursor.getColumnIndex("id"))),
                                      cursor.getString(cursor.getColumnIndex("nombre")),
                                      cursor.getString(cursor.getColumnIndex("registro")),
                                      cursor.getString(cursor.getColumnIndex("actualizado"))));
                cursor.moveToNext();
            }
            cursor.close();
        }*/
        sqLiteDB.close();
        return registryArrayList;
    }

    public static String getMaxId(){

        initializeDatabase();
        initializeReadableDatabase();

        //TODO - remove hardcoded query string.

        final SQLiteStatement stmt = sqLiteDatabase
                .compileStatement("SELECT MAX(id) FROM empresa");

        return ""+(int) stmt.simpleQueryForLong();

    }

    public static String getMaxIdFromRegistry(){

        initializeDatabase();
        initializeReadableDatabase();

        //TODO - remove hardcoded query string.

        final SQLiteStatement stmt = sqLiteDatabase
                .compileStatement("SELECT MAX(id) FROM registro_form");

        return ""+(int) stmt.simpleQueryForLong();

    }

}