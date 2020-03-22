/*
 * Copyright (c) 2020 Helmut Neemann.
 * Use of this source code is governed by the GPL v3 license
 * that can be found in the LICENSE file.
 */
package de.neemann.digital.core.extern;

import de.neemann.digital.core.element.Key;
import de.neemann.digital.gui.Settings;

import java.util.ArrayList;

/**
 * Used to split option strings to a option list
 */
public class Options {

    private final ArrayList<String> list;

    /**
     * Creates a new instance
     */
    public Options() {
        list = new ArrayList<>();
    }

    /**
     * Adds a string from the settings
     *
     * @param key the key to use
     * @return this for chained calls
     */
    public Options addSettings(Key<String> key) {
        return addString(Settings.getInstance().get(key));
    }

    /**
     * Adds a string containing many options
     *
     * @param options the string containing the options
     * @return this for chained calls
     */
    public Options addString(String options) {
        StringBuilder opt = new StringBuilder();
        boolean inQuote = false;
        for (int i = 0; i < options.length(); i++) {
            char c = options.charAt(i);
            if (c == '"')
                inQuote = !inQuote;

            if (Character.isWhitespace(c) && !inQuote) {
                if (opt.length() > 0)
                    list.add(opt.toString());
                opt.setLength(0);
            } else {
                opt.append(c);
            }
        }
        if (opt.length() > 0)
            list.add(opt.toString());
        return this;
    }

    /**
     * Adds a single raw option
     *
     * @param option the options to add
     * @return this for chained calls
     */
    public Options add(String option) {
        list.add(option);
        return this;
    }

    /**
     * @return the options as a list
     */
    public ArrayList<String> getList() {
        return list;
    }

    /**
     * @return the options as an array
     */
    public String[] getArray() {
        return list.toArray(new String[0]);
    }
}