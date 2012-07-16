/*
 * Copyright (C) 2011 Steven Luo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package evervolv.androidterm.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collection;

import evervolv.androidterm.emulatorview.TermSession;
import evervolv.androidterm.emulatorview.UpdateCallback;

/**
 * An ArrayList of TermSessions which allows users to register callbacks in
 * order to be notified when the list is changed.
 */
@SuppressWarnings("serial")
public class SessionList extends ArrayList<TermSession>
{
    LinkedList<UpdateCallback> callbacks = new LinkedList<UpdateCallback>();

    public SessionList() {
        super();
    }

    public SessionList(int capacity) {
        super(capacity);
    }

    public void addCallback(UpdateCallback callback) {
        callbacks.add(callback);
    }

    public boolean removeCallback(UpdateCallback callback) {
        return callbacks.remove(callback);
    }

    private void notifyChange() {
        for (UpdateCallback callback : callbacks) {
            callback.onUpdate();
        }
    }

    @Override
    public boolean add(TermSession object) {
        boolean result = super.add(object);
        notifyChange();
        return result;
    }

    @Override
    public void add(int index, TermSession object) {
        super.add(index, object);
        notifyChange();
    }

    @Override
    public boolean addAll(Collection <? extends TermSession> collection) {
        boolean result = super.addAll(collection);
        notifyChange();
        return result;
    }

    @Override
    public boolean addAll(int index, Collection <? extends TermSession> collection) {
        boolean result = super.addAll(index, collection);
        notifyChange();
        return result;
    }

    @Override
    public void clear() {
        super.clear();
        notifyChange();
    }

    @Override
    public TermSession remove(int index) {
        TermSession object = super.remove(index);
        if (object != null) {
            notifyChange();
        }
        return object;
    }

    @Override
    public boolean remove(Object object) {
        boolean result = super.remove(object);
        if (result) {
            notifyChange();
        }
        return result;
    }

    @Override
    public TermSession set(int index, TermSession object) {
        TermSession old = super.set(index, object);
        notifyChange();
        return old;
    }
}
