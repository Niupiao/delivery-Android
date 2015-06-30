package com.niupiao.deliveryapp.Map;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.niupiao.deliveryapp.Deliveries.DataSource;
import com.niupiao.deliveryapp.Deliveries.Delivery;
import com.niupiao.deliveryapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Inanity on 6/22/2015.
 */
public class MapFragment extends android.support.v4.app.Fragment {
    MapView mMapView;
    private GoogleMap mMap;
    private ArrayList<Delivery> mInProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate and return the layout
        View v = inflater.inflate(R.layout.fragment_map_tab, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        Button refreshButton = (Button) v.findViewById(R.id.refresh_markers);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Updating...", Toast.LENGTH_LONG).show();
                updateMarkers();
            }
        });

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMap = mMapView.getMap();

        mInProgress = DataSource.get(getActivity().getApplicationContext()).getInProgress();

        for (Delivery d : mInProgress) {
            MarkerOptions pickupMarker = new MarkerOptions().position(
                    getLatLongFromAddress(d.getPickupAddress())).title(d.getPickupAddress() + " Pickup");

            MarkerOptions dropoffMarker = new MarkerOptions().position(
                    getLatLongFromAddress(d.getDropoffAddress())).title(d.getDropoffAddress() + " Drop Off");

            pickupMarker.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

            dropoffMarker.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_RED));

            mMap.addMarker(pickupMarker);
            mMap.addMarker(dropoffMarker);
        }

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(117.2, 32.7)).zoom(5).build();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        // Perform any camera updates here
        return v;
    }

    private LatLng getLatLongFromAddress(String address) {
        double lat = 0.0, lng = 0.0;

        Geocoder geoCoder = new Geocoder(this.getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocationName(address, 1);
            if (addresses.size() > 0) {
                LatLng p = new LatLng(
                        addresses.get(0).getLatitude(),
                        addresses.get(0).getLongitude());

                lat = p.latitude;
                lng = p.longitude;

                Log.d("Coordinates", "Lat: " + lat + " Long: " + lng);
                return p;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateMarkers() {
        mMap = mMapView.getMap();

        mInProgress = DataSource.get(getActivity().getApplicationContext()).getInProgress();

        for (Delivery d : mInProgress) {
            MarkerOptions pickupMarker = new MarkerOptions().position(
                    getLatLongFromAddress(d.getPickupAddress())).title(d.getPickupAddress() + " Pickup");

            MarkerOptions dropoffMarker = new MarkerOptions().position(
                    getLatLongFromAddress(d.getDropoffAddress())).title(d.getDropoffAddress() + " Drop Off");

            pickupMarker.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

            dropoffMarker.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_RED));

            mMap.addMarker(pickupMarker);
            mMap.addMarker(dropoffMarker);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}