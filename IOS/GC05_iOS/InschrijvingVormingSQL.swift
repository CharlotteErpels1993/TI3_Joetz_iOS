import Foundation

struct InschrijvingVormingSQL {
    
    static func createInschrijvingVormingTable() {
        if let error = SD.createTable("InschrijvingVorming", withColumnNamesAndTypes: ["objectId": .StringVal, "monitor": .StringVal/*, "periode": .StringVal*/,
             "vorming": .StringVal])
        {
            println("ERROR: error tijdens creatie van table InschrijvingVorming")
        }
        else
        {
            //no error
        }
    }
    
    static func vulInschrijvingVormingTableOp() {
        var inschrijvingenVorming: [PFObject] = []
        var query = PFQuery(className: "InschrijvingVorming")
        inschrijvingenVorming = query.findObjects() as [PFObject]
        
        var queryString = ""
        
        var objectId: String = ""
        var monitor: String = ""
        //var periode: String = ""
        var vorming: String = ""
        
        for inschrijvingVorming in inschrijvingenVorming {
            
            queryString.removeAll(keepCapacity: true)
            
            objectId = inschrijvingVorming.objectId as String
            monitor = inschrijvingVorming["monitor"] as String
            //periode = inschrijvingVorming["periode"] as String
            vorming = inschrijvingVorming["vorming"] as String
            
            queryString.extend("INSERT INTO InschrijvingVorming ")
            queryString.extend("(")
            queryString.extend("objectId, ")
            queryString.extend("monitor, ")
            //queryString.extend("periode, ")
            queryString.extend("vorming")
            queryString.extend(")")
            queryString.extend(" VALUES ")
            queryString.extend("(")
            
            queryString.extend("'\(objectId)', ") //objectId - String
            queryString.extend("'\(monitor)', ") //monitorId - String
            //queryString.extend("'\(periode)', ") //periode - String
            queryString.extend("'\(vorming)'") //vormingId - String
            
            queryString.extend(")")
            
            
            if let err = SD.executeChange(queryString)
            {
                println("ERROR: error tijdens toevoegen van nieuwe InschrijvingVorming in table InschrijvingVorming")
            }
            else
            {
                //no error, the row was inserted successfully
            }
            
        }
    }
    
    static func parseInschrijvingVormingToDatabase(inschrijving: InschrijvingVorming) {
        var inschrijvingJSON = PFObject(className: "InschrijvingVorming")
        
        //inschrijvingJSON.setValue(inschrijving.periode, forKey: "periode")
        inschrijvingJSON.setValue(inschrijving.monitor?.id, forKey: "monitor")
        inschrijvingJSON.setValue(inschrijving.vorming?.id, forKey: "vorming")
        
        inschrijvingJSON.save()
    }

    static func getVormingIdMetMonitorId(monitorId: String) -> /*[String]*/ ([String], Int?) {
        var vormingenIds: [String] = []
        
        let (resultSet, err) = SD.executeQuery("SELECT vorming FROM InschrijvingVorming WHERE monitor = ?", withArgs: [monitorId])
        
        var response: ([String], Int?)
        var error: Int?
        
        if err != nil
        {
            println("ERROR: error tijdens ophalen van VormingenIds van Monitor van table InschrijvingVorming")
        }
        else
        {
            if resultSet.count == 0 {
                error = 1
            }
            else {
                error = nil
                
                for row in resultSet {
                    if let vormingId = row["vorming"]?.asString() {
                        vormingenIds.append(vormingId)
                    }
                }
            }
            
        }
        
        //return vormingenIds
        response = (vormingenIds, error)
        return response
    }
    
    static func getMonitorsIdMetVormingId(vormingen: [String]) -> /*[String]*/ ([String], Int?) {
        var monitorsId: [String] = []
        
        var response: ([String], Int?)
        var error: Int?
        
        for vorming in vormingen {
            var (resultSet, err) = SD.executeQuery("SELECT monitor FROM InschrijvingVorming WHERE vorming = ?", withArgs: [vorming])
            
            if err != nil
            {
                println("ERROR: error tijdens ophalen van monitorIds van monitors met dezelfde vorming uit table InschrijvingVorming")
            }
            else
            {
                for row in resultSet {
                    if let monitorId = row["monitor"]?.asString() {
                        if !contains(monitorsId, monitorId) {
                            monitorsId.append(monitorId)
                        }
                    }
                }
            }
        }
        
        if monitorsId.count == 0 {
            error = 1
        }
        else {
            error = nil
        }
        
        response = (monitorsId, error)
        
        return response
    }
    
    static func getInschrijvingenVorming(monitorId: String, vormingId: String/*, periode: String*/) -> [InschrijvingVorming] {
        var inschrijvingen: [InschrijvingVorming] = []
        var inschrijving: InschrijvingVorming = InschrijvingVorming(id: "test")
        var queryString: String = ""
        
        queryString.extend("SELECT * FROM InschrijvingVorming ")
        queryString.extend("WHERE monitor = ? ")
        queryString.extend("AND ")
        queryString.extend("vorming = ?")
        //queryString.extend("AND ")
        //queryString.extend("periode = ?")
        
        let (resultSet, err) = SwiftData.executeQuery(queryString, withArgs: [monitorId, vormingId/*, periode*/])
        
        if err != nil
        {
            println("ERROR: error tijdens ophalen van inschrijvingenMonitor uit table InschrijvingenVorming")
        }
        else
        {
            if resultSet.count > 0 {
                inschrijvingen.append(inschrijving)
                inschrijvingen.append(inschrijving)
            }
        }
        
        return inschrijvingen
    }
    
}