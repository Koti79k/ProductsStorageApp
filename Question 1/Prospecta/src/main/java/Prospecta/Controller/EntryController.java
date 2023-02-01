package Prospecta.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import Prospecta.Entity.Data;
import Prospecta.Entity.Entry;
import Prospecta.Entity.EntryDto;
import Prospecta.Repositery.EntryRepo;

@RestController
public class EntryController {

	@Autowired
	private RestTemplate template;
	
	@Autowired
	private EntryRepo eController;
	
	String uri="https://api.publicapis.org/entries";
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<EntryDto>> getListOfEntries(@PathVariable("category") String category){
		Data data=template.getForObject(uri, Data.class);
		List<Entry> getEntries=data.getEntries();
		List<EntryDto> getList=getEntries.stream().filter(entry -> entry.getCategory().equalsIgnoreCase(category))
				.map(entry -> new EntryDto(entry.getApi(),entry.getDescription())).toList();
		return new ResponseEntity<List<EntryDto>>(getList,HttpStatus.OK);
		
	}
	
	@PostMapping("/entries")
	public ResponseEntity<Entry> saveEntryUsingRequestBody(@RequestBody EntryDto entryDto){
		Data data=template.getForObject(uri, Data.class);
		Entry e1=data.getEntries().get(0);
		Entry e2=new Entry(null, entryDto.getTitle(),entryDto.getDescription(),e1.getAuth(),e1.isHTTPS(),e1.getCors(),e1.getLink(),e1.getCategory());
		
		Entry savedEntry=eController.save(e2);
		return new ResponseEntity<Entry>(savedEntry,HttpStatus.OK);
	}
}






