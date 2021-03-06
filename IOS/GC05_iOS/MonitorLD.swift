import Foundation

struct MonitorLD {
    
    //
    //Function: getMonitoren
    //
    //Deze functie zet een array van PFObject om naar een array van Monitor.
    //
    //Parameters: - objecten: [PFObject]
    //
    //Return: een array van Monitor
    //
    static func getMonitoren(objecten: [PFObject]) -> [Monitor] {
        var monitoren: [Monitor] = []
        
        for object in objecten {
            monitoren.append(getMonitor(object))
        }
        
        return monitoren
    }
    
    //
    //Function: getMonitor
    //
    //Deze functie zet een  PFObject om naar een Monitor.
    //
    //Parameters: - object: PFObject
    //
    //Return: een Monitor
    //
    static func getMonitor(object: PFObject) -> Monitor {
        var monitor: Monitor = Monitor(id: object.objectId)
        
        monitor.rijksregisterNr = object[Constanten.COLUMN_RIJKSREGISTERNUMMER] as? String
        monitor.email = object[Constanten.COLUMN_EMAIL] as? String
        monitor.voornaam = object[Constanten.COLUMN_VOORNAAM] as? String
        monitor.naam = object[Constanten.COLUMN_NAAM] as? String
        monitor.straat = object[Constanten.COLUMN_STRAAT] as? String
        monitor.nummer = object[Constanten.COLUMN_NUMMER] as? Int
        monitor.postcode = object[Constanten.COLUMN_POSTCODE] as? Int
        monitor.gemeente = object[Constanten.COLUMN_GEMEENTE] as? String
        monitor.gsm = object[Constanten.COLUMN_GSM] as? String
        monitor.lidNr = object[Constanten.COLUMN_LIDNUMMER] as? String
        
        if object[Constanten.COLUMN_BUS] != nil {
            monitor.bus = object[Constanten.COLUMN_BUS] as? String
        } else {
            monitor.bus = ""
        }
        
        if object[Constanten.COLUMN_TELEFOON] != nil {
            monitor.telefoon = object[Constanten.COLUMN_TELEFOON] as? String
        } else {
            monitor.telefoon = ""
        }
        
        if object[Constanten.COLUMN_AANSLUITINGSNUMMER] != nil {
            monitor.aansluitingsNr = object[Constanten.COLUMN_AANSLUITINGSNUMMER] as? Int
        } else {
            monitor.aansluitingsNr = 0
        }
        
        if object[Constanten.COLUMN_CODEGERECHTIGDE] != nil {
            monitor.codeGerechtigde = object[Constanten.COLUMN_CODEGERECHTIGDE] as? Int
        } else {
            monitor.codeGerechtigde = 0
        }
        
        return monitor
    }
    
    //
    //Function: insert
    //
    //Deze functie insert een Monitor object in de local datastore en
    //synct deze verandering dan naar de online database.
    //
    //Parameters: - monitor: Monitor
    //
    static func insert(monitor: Monitor) {
        
        let object = PFObject(className: Constanten.TABLE_MONITOR)
        
        object[Constanten.COLUMN_RIJKSREGISTERNUMMER] = monitor.rijksregisterNr
        object[Constanten.COLUMN_EMAIL] = monitor.email
        object[Constanten.COLUMN_VOORNAAM] = monitor.voornaam
        object[Constanten.COLUMN_NAAM] = monitor.naam
        object[Constanten.COLUMN_STRAAT] = monitor.straat
        object[Constanten.COLUMN_NUMMER] = monitor.nummer
        object[Constanten.COLUMN_POSTCODE] = monitor.postcode
        object[Constanten.COLUMN_GEMEENTE] = monitor.gemeente
        object[Constanten.COLUMN_GSM] = monitor.gsm
        
        if monitor.bus != nil {
            object[Constanten.COLUMN_BUS] = monitor.bus
        }
        
        if monitor.telefoon != nil {
            object[Constanten.COLUMN_TELEFOON] = monitor.telefoon
        }
        
        if monitor.aansluitingsNr != nil {
            object[Constanten.COLUMN_AANSLUITINGSNUMMER] = monitor.aansluitingsNr
        }
        
        if monitor.codeGerechtigde != nil {
            object[Constanten.COLUMN_CODEGERECHTIGDE] = monitor.codeGerechtigde
        }
        
        if monitor.lidNr != nil {
            object[Constanten.COLUMN_LIDNUMMER] = monitor.lidNr
        }
        
        object.pin()
        object.save()
    }
    
    static func update(object: PFObject, monitor: Monitor) -> Monitor {
        
        object[Constanten.COLUMN_RIJKSREGISTERNUMMER] = monitor.rijksregisterNr
        object[Constanten.COLUMN_EMAIL] = monitor.email
        object[Constanten.COLUMN_VOORNAAM] = monitor.voornaam
        object[Constanten.COLUMN_NAAM] = monitor.naam
        object[Constanten.COLUMN_STRAAT] = monitor.straat
        object[Constanten.COLUMN_NUMMER] = monitor.nummer
        object[Constanten.COLUMN_POSTCODE] = monitor.postcode
        object[Constanten.COLUMN_GEMEENTE] = monitor.gemeente
        object[Constanten.COLUMN_GSM] = monitor.gsm
        
        if monitor.bus != nil {
            object[Constanten.COLUMN_BUS] = monitor.bus
        }
        
        if monitor.telefoon != nil {
            object[Constanten.COLUMN_TELEFOON] = monitor.telefoon
        }
        
        if monitor.aansluitingsNr != nil {
            object[Constanten.COLUMN_AANSLUITINGSNUMMER] = monitor.aansluitingsNr
        }
        
        if monitor.codeGerechtigde != nil {
            object[Constanten.COLUMN_CODEGERECHTIGDE] = monitor.codeGerechtigde
        }
        
        if monitor.lidNr != nil {
            object[Constanten.COLUMN_LIDNUMMER] = monitor.lidNr
        }
        
        object.save()
        
        return getMonitor(object)
    }
}