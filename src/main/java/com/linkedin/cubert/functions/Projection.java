/* (c) 2014 LinkedIn Corp. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 */

package com.linkedin.cubert.functions;

import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;

import com.linkedin.cubert.block.BlockSchema;
import com.linkedin.cubert.block.ColumnType;
import com.linkedin.cubert.operator.PreconditionException;

/**
 * Function that selects a column from nested tuples.
 * 
 * @author Maneesh Varshney
 * 
 */
public class Projection extends Function
{
    private final int selector;

    public Projection(int selector)
    {
        this.selector = selector;
    }

    @Override
    public Object eval(Tuple tuple) throws ExecException
    {
        final Tuple nestedTuple = (Tuple) tuple.get(0);
        return nestedTuple == null? null: nestedTuple.get(selector);
    }

    @Override
    public ColumnType outputSchema(BlockSchema inputSchema) throws PreconditionException
    {
        return inputSchema.getColumnType(selector);
    }

}