/**
 * Copyright 2013-2015 Pierre Merienne
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pmerienne.trident.ml.preprocessing;

import com.github.pmerienne.trident.ml.core.Instance;
import com.github.pmerienne.trident.ml.util.MathUtil;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;
import backtype.storm.tuple.Values;

public class Normalizer extends BaseFunction {

	private static final long serialVersionUID = 511416266460297754L;

	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		Instance<?> instance = (Instance<?>) tuple.get(0);
		Instance<?> normalizedInstance = this.normalize(instance);
		collector.emit(new Values(normalizedInstance));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Instance<?> normalize(Instance<?> instance) {
		double[] normalizedFeatures = MathUtil.normalize(instance.features);
		Instance<?> normalizedInstance = new Instance(instance.label, normalizedFeatures);
		return normalizedInstance;
	}
}
