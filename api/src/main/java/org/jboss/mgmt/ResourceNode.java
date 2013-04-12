/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.mgmt;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
public abstract class ResourceNode<R extends Resource> implements Resource {
    private volatile R current;
    private final ResourceNode<?> parent;

    protected ResourceNode(final ResourceNode<?> parent) {
        this.parent = parent;
    }

    protected R getCurrent() {
        return current;
    }

    protected void modify(R newValue) {
        current = newValue;
    }

    public String getPreComment() {
        return current.getPreComment();
    }

    public String getPostComment() {
        return current.getPostComment();
    }

    public String getName() {
        return current.getName();
    }

    public Resource getParent() {
        return parent;
    }

    public abstract Resource navigate(final String key, final String value);

    public abstract void toXml(XMLStreamWriter writer) throws XMLStreamException;

    @SuppressWarnings("unchecked")
    R asResource() {
        return (R) this;
    }
}
