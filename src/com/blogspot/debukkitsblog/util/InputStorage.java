package com.blogspot.debukkitsblog.util;

import com.blogspot.debukkitsblog.crypt.CryptedObject;
import com.blogspot.debukkitsblog.crypt.Crypter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class InputStorage {

    private InputStream inputFile;
    private HashMap<String, Object> storageMap;

    private boolean autosave;

    /**
     * Creates a FileStorage. It allows you to store your serializable object in a
     * file using a key for identification and to read it somewhen later.<br>
     * All data <code>store</code>d in this FileStorage will instantly be stored in
     * the given file. This might cause many write operations on disk.
     *
     * @param autosave
     *            Whether every <code>store</code> operation shall automatically
     *            write the whole FileStorage to disk. If false, you will need to
     *            call the <code>save</code> method manually.
     * @param file
     *            The file your data shall be stored in
     * @throws IOException
     *             if the file cannot be created
     * @throws IllegalArgumentException
     *             if the file is a directory
     */
    public InputStorage(InputStream file, boolean autosave) throws IllegalArgumentException, IOException {
        this(file);
        this.autosave = autosave;
    }

    /**
     * Creates a FileStorage. It allows you to store your serializable object in a
     * file using a key for identification and to read it somewhen later.<br>
     * All data <code>store</code>d in this FileStorage will instantly be stored in
     * the given file. This might cause many write operations on disk.
     *
     * @param input
     *            The file your data shall be stored in
     * @throws IOException
     *             if your file cannot be created
     * @throws IllegalArgumentException
     *             if your file is a directory
     */
    public InputStorage(InputStream input) throws IOException, IllegalArgumentException {
        this.inputFile = input;
        autosave = true;
        load();

    }

    /**
     * Loads the FileStorage from the file
     */
    @SuppressWarnings("unchecked")
    private void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(inputFile));
            storageMap = (HashMap<String, Object>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads your object from the storage.<br>
     * <br>
     * Use <u>get(String key, String password)</u> for AES encrypted objects.
     *
     * @param key
     *            The key the object is available under
     * @return your Object or null if nothing was found for <i>key</i>
     */
    public Object get(String key) {
        return storageMap.get(key);
    }

    /**
     * Reads your AES encrypted object from the storage.<br>
     * <br>
     * Use <u>get(String key)</u> instead for unencrypted objects.
     *
     * @param key
     *            The key the object is available under
     * @param password
     *            The password to use for decryption
     * @return your object or null if nothing was found for <i>key</i> or if
     *         decryption failed (wrong password)
     * @throws Crypter.DecryptionFailedException
     *             This usually happens if the password is wrong
     */
    public Object get(String key, String password) throws Crypter.DecryptionFailedException {
        if (storageMap.get(key) instanceof CryptedObject) {
            return Crypter.decrypt((CryptedObject) get(key), password);
        } else {
            return get(key);
        }
    }

    /**
     * All stored objects in an ArrayList of Objects
     *
     * @return all stored objects in an ArrayList of Objects
     */
    public ArrayList<Object> getAllAsArrayList() {
        ArrayList<Object> result = new ArrayList<Object>();
        for (Object c : storageMap.values()) {
            result.add(c);
        }
        return result;
    }

    /**
     * All stored objects in a HashMap of Strings and Objects
     *
     * @return all stored objects in a HashMap of Strings and Objects
     */
    public HashMap<String, Object> getAll() {
        return storageMap;
    }

    /**
     * Prints all stored keys with corresponding objects
     */
    public void printAll() {
        System.out.println(this);
    }

    /**
     * Checks whether a key is registerd
     *
     * @param key
     *            The Key.
     * @return true if an object is available for that key
     */
    public boolean hasKey(String key) {
        return storageMap.containsKey(key);
    }

    /**
     * Checks whether an object is stored at all
     *
     * @param o
     *            The Object.
     * @return true if the object is stored
     */
    public boolean hasObject(Object o) {
        return storageMap.containsValue(o);
    }

    /**
     * Returns the number of objects (elements) stored
     *
     * @return The number of objects (elements) stored
     */
    public int getSize() {
        return storageMap.size();
    }

}