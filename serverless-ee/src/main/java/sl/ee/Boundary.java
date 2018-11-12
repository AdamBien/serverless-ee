
package sl.ee;

import javax.json.JsonObject;

/**
 *
 * @author airhacks.com
 */
public interface Boundary {

    JsonObject handleRequest(JsonObject input);

}
