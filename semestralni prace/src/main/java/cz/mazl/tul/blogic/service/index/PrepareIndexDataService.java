package cz.mazl.tul.blogic.service.index;

import cz.mazl.tul.dto.out.index.IndexCountryDataDTO;
import cz.mazl.tul.dto.out.index.IndexDataDTO;

import java.util.List;

public interface PrepareIndexDataService {

    /***
     * This method retunrs country data, city data and their temperature. It's used for showing data in table.
     *
     * @return IndexDataDTO
     */
    IndexDataDTO prepare();

    /**
     * Returns data identified by country(isoCode). This method is used for REST api which then render all results to HTML.
     *
     * @param countryCode
     * @return
     */
    List<IndexCountryDataDTO> getDataForCountry(String countryCode);
}
