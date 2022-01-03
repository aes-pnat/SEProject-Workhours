import React from 'react';
import '../Map.css'

class Map extends React.Component {
    mapRef = React.createRef();

    state = {
        map: null
    };

    fetchData = async () => {
        let dataList = [];

        const myHeaders = new Headers();
        myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");

        await fetch(process.env.REACT_APP_BACKEND_URL + '/map', {
            method: 'GET',
            headers: myHeaders
        }).then(response => {
            if (response.ok) {
                return response.json();
            }
        }).then(jsonResponse => {
            dataList = jsonResponse;
        }).catch(err => {
            throw err;
        });

        return dataList;
    }

    componentDidMount = async() => {
        const H = window.H;
        const platform = new H.service.Platform({
            apikey: "_avtU1QAAd7wlen69G44690--o6AVuc_kiN9O-qwGe0"
        });

        const defaultLayers = platform.createDefaultLayers();

        // Create an instance of the map
        const map = new H.Map(
            this.mapRef.current,
            defaultLayers.vector.normal.map,
            {
                center: { lat: 45.8150, lng: 15.9819 },
                zoom: 8,
                pixelRatio: window.devicePixelRatio || 1
            }
        );

        // MapEvents enables the event system
        // Behavior implements default interactions for pan/zoom (also on mobile touch environments)
        // This variable is unused and is present for explanatory purposes
        const behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

        // Create the default UI components to allow the user to interact with them
        // This variable is unused
        const ui = H.ui.UI.createDefault(map, defaultLayers);
        
        
        var group = new H.map.Group();
        map.addObject(group);
        group.addEventListener('tap', function (e) {
            var bubble = new H.ui.InfoBubble(e.target.getGeometry(), {
                content: e.target.getData()
            });
            ui.addBubble(bubble);
        }, false);
        
        let taskList = await this.fetchData();

        taskList.forEach(task => {
            let coords = {
                lat: task.location.latitude, 
                lng: task.location.longitude
            };

            let marker = new H.map.Marker(coords);
            marker.setData(
                `<div class="infobubble-container">
                    <div class="fw-bold">Lokacija:</div>
                    <div>${task.location.placename}</div>
                    <div class="mb-3">${task.location.address}</div>
                    <div class="fw-bold">Djelatnik:</div>
                    <div class="mb-3">${task.employeeName} ${task.employeeSurname}</div>
                    <div class="fw-bold">Datum i vrijeme intervencije:</div>
                    <div>
                        Od ${(new Date(task.startDateAndTime)).toLocaleString('en-GB')} do
                        ${(new Date(task.endDateAndTime)).toLocaleString('en-GB')}
                    </div>
                </div>`
            );

            group.addObject(marker);
        });
        
        this.setState({ map });
    }


    componentWillUnmount() {
        // Cleanup after the map to avoid memory leaks when this component exits the page
        this.state.map.dispose();
    }

    render() {
        return (
            <div className="container">
                <div className="h3 mt-3">Prikaz intervencija</div>
                <div className="mt-5" ref={this.mapRef} style={{ height: "80vh" }} />
            </div>
        );
    }
}

export default Map;