/*
 * Copyright (c) 2014-2018 Globo.com - ATeam
 * All rights reserved.
 *
 * This source is subject to the Apache License, Version 2.0.
 * Please see the LICENSE file for more information.
 *
 * Authors: See AUTHORS file
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.galeb.oldapi.controllers;

import io.galeb.oldapi.services.PoolService;
import io.galeb.oldapi.v1entities.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/pool")
public class PoolController {

    @Autowired
    private PoolService service;

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> get() {
        return service.get();
    }

    @RequestMapping(value = "/{param:.+}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> getWithParam(@PathVariable String param) {
        return service.getWithParam(param);
    }

    @RequestMapping(method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> post() {
        return service.post();
    }

    @RequestMapping(value = "/{param:.+}", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> postWithParam(@PathVariable String param) {
        return service.postWithParam(param);
    }

    @RequestMapping(method = PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> put() {
        return service.put();
    }

    @RequestMapping(value = "/{param:.+}", method = PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> putWithParam(@PathVariable String param) {
        return service.putWithParam(param);
    }

    @RequestMapping(method = DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> delete() {
        return service.delete();
    }

    @RequestMapping(value = "/{param:.+}", method = DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> deleteWithParam(@PathVariable String param) {
        return service.deleteWithParam(param);
    }

    @RequestMapping(method = PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> patch() {
        return service.patch();
    }

    @RequestMapping(value = "/{param:.+}", method = PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> patchWithParam(@PathVariable String param) {
        return service.patchWithParam(param);
    }

    @RequestMapping(method = OPTIONS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> options() {
        return service.options();
    }

    @RequestMapping(value = "/{param:.+}", method = OPTIONS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> optionsWithParam(@PathVariable String param) {
        return service.optionsWithParam(param);
    }

    @RequestMapping(method = HEAD, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> head() {
        return service.head();
    }

    @RequestMapping(value = "/{param:.+}", method = HEAD, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> headWithParam(@PathVariable String param) {
        return service.headWithParam(param);
    }

    @RequestMapping(method = TRACE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> trace() {
        return service.trace();
    }

    @RequestMapping(value = "/{param:.+}", method = TRACE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pool> traceWithParam(@PathVariable String param) {
        return service.traceWithParam(param);
    }

}