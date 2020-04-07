package cz.mazl.tul.blogic.service.index;

import cz.mazl.tul.dto.out.index.IndexCountryDataDTO;
import cz.mazl.tul.dto.out.index.IndexDataDTO;

import java.util.List;

public interface PrepareIndexDataService {
    IndexDataDTO prepare();

    List<IndexCountryDataDTO> getDataForCountry(String countryCode);
}
