package me.passtheheadphones.ui.profile;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.passtheheadphones.PTHApplication;
import me.passtheheadphones.base.BaseActivity;
import me.passtheheadphones.data.model.Profile;
import me.passtheheadphones.util.DialogFactory;
import me.passtheheadphones.app.R;

public class ProfileActivity extends BaseActivity implements ProfileMvpView {

    @Inject
    ProfilePresenter mProfilePresenter;

    @BindView(R.id.avatar) public ImageView avatar;
    @BindView(R.id.loading_indicator) public ProgressBar spinner;
    @BindView(R.id.art_container) public View artContainer;
    @BindView(R.id.username) public TextView username;
    @BindView(R.id.user_class) public TextView userClass;
    @BindView(R.id.joined) public TextView joined;
    @BindView(R.id.invited) public TextView invited;
    @BindView(R.id.ratio) public TextView ratio;
    @BindView(R.id.paranoia) public TextView paranoia;

    @BindView(R.id.invited_text) public TextView invitedText;
    @BindView(R.id.ratio_text) public TextView ratioText;
    @BindView(R.id.paranoia_text) public TextView paranoiaText;

//    private RecentTorrentPagerAdapter snatchesAdapter;
//    private RecentTorrentPagerAdapter uploadsAdapter;
    @BindView(R.id.snatches_container) public View snatchesContainer;
    @BindView(R.id.uploads_container) public View uploadsContainer;
    @BindView(R.id.donor) public View donor;
    @BindView(R.id.warned) public View warned;
    @BindView(R.id.banned) public View banned;

    @BindView(R.id.data_uploaded_text) public TextView dataUploadedText;
    @BindView(R.id.data_uploaded) public TextView dataUploaded;
    @BindView(R.id.data_downloaded_text) public TextView dataDownloadedText;
    @BindView(R.id.data_downloaded) public TextView dataDownloaded;
    @BindView(R.id.torrents_uploaded_text) public TextView torrentsUploadedText;
    @BindView(R.id.torrents_uploaded) public TextView torrentsUploaded;
    @BindView(R.id.requests_filled_text) public TextView requestsFilledText;
    @BindView(R.id.requests_filled) public TextView requestsFilled;
    @BindView(R.id.bounty_spent_text) public TextView bountySpentText;
    @BindView(R.id.bounty_spent) public TextView bountySpent;
    @BindView(R.id.posts_made_text) public TextView postsMadeText;
    @BindView(R.id.posts_made) public TextView postsMade;
    @BindView(R.id.artists_added_text) public TextView artistsAddedText;
    @BindView(R.id.artists_added) public TextView artistsAdded;
    @BindView(R.id.overall_text) public TextView overallText;
    @BindView(R.id.overall) public TextView overall;

    @BindView(R.id.data_uploaded_value) public TextView dataUploadedValue;
    @BindView(R.id.data_downloaded_value) public TextView dataDownloadedValue;
    @BindView(R.id.torrents_uploaded_value) public TextView torrentsUploadedValue;
    @BindView(R.id.requests_filled_value) public TextView requestsFilledValue;
    @BindView(R.id.posts_made_value) public TextView postsMadeValue;

    @BindView(R.id.ranks_header_container) public CardView toggleRanks;
    @BindView(R.id.info_box) public CardView toggleStats;
    @BindView(R.id.snatches_header_container) public CardView toggleSnatches;
    @BindView(R.id.uploads_header_container) public CardView toggleUploads;

    @BindView(R.id.toggle_stats) public ImageView toggleStatsBtn;
    @BindView(R.id.toggle_ranks) public ImageView toggleRanksBtn;
    @BindView(R.id.toggle_snatches) public ImageView toggleSnatchesBtn;
    @BindView(R.id.toggle_uploads) public ImageView toggleUploadsBtn;

    @BindView(R.id.user_ranks) public RelativeLayout ranksView;
    @BindView(R.id.user_stats) public RelativeLayout statsView;
//    @BindView(R.id.recent_snatches) public WrappingViewPager snatchesView;
//    @BindView(R.id.recent_uploads) public WrappingViewPager uploadsView;

    private String messageDraft = "", messageSubject = "";

    private MenuItem sendMessage;

    /**
     * Android activity lifecycle methods
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.fragment_profile);
        ButterKnife.bind(this);

        mProfilePresenter.attachView(this);
        mProfilePresenter.loadProfile();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mProfilePresenter.detachView();
    }

    /***** MVP View methods implementation *****/

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_profile))
                .show();
    }

    @Override
    public void showProfile(Profile profile) {

        //TODO how much of this should be processed in presenter?
//        this.getActionBar().setTitle(profile.response.username);
        username.setText(profile.response.username);
        userClass.setText(profile.response.personal.mclass);
//        Date joinDate = new Date(Date.parse(profile.response.stats.joinedDate));
        joined.setText(getString(R.string.joined) + ": " +  DateUtils.getRelativeTimeSpanString(profile.response.stats.joinedDate.getTime(),
                new Date().getTime(), DateUtils.WEEK_IN_MILLIS));

        //We need to check all the paranoia cases that may cause a field to be missing and hide the views for it
        String avatarUrl = profile.response.avatar;

        //TODO get this from preference helper
//        if (!SettingsActivity.imagesEnabled(this)) {
//            artContainer.setVisibility(View.GONE);
//        } else {
//            artContainer.setVisibility(View.VISIBLE);
//            PTHApplication.loadImage(this, avatarUrl, avatar, spinner, null, null);
//        }

        //TODO get this from index
//        if (profile.response.personal.paranoia > 0 && userID != MySoup.getUserId()) {
            paranoiaText.setVisibility(View.VISIBLE);
            paranoia.setText(profile.response.personal.paranoiaText);
//        } else {
//            paranoia.setVisibility(View.GONE);
//        }

        //TODO handle paranoia
//        if (profile.response.community.invited ) {
//            invited.setText(profile.response.community.invited);
//        } else {
//            invitedText.setVisibility(View.GONE);
//            invited.setVisibility(View.GONE);
//        }
//        if (profile.response.stats.ratio != null && profile.getStats().getRequiredRatio() != null) {
            ratio.setText(profile.response.stats.ratio
                    + " / " + profile.response.stats.requiredRatio);
//        } else {
//            ratioText.setVisibility(View.GONE);
//            ratio.setVisibility(View.GONE);
//        }
        //TODO: use other api call, load this info in another method
//        UserReceecRecents recentTorrents = userProfile.getUserRecents();
//        if (profile.getPersonal().getParanoia().intValue() < 6 || userID == MySoup.getUserId()) {
//            if (recentTorrents.getSnatches().size() > 0) {
//                snatchesAdapter.onLoadingComplete(recentTorrents.getSnatches());
//                snatchesAdapter.notifyDataSetChanged();
//            } else {
//                snatchesContainer.setVisibility(View.GONE);
//            }
//            if (recentTorrents.getUploads().size() > 0) {
//                uploadsAdapter.onLoadingComplete(recentTorrents.getUploads());
//                uploadsAdapter.notifyDataSetChanged();
//            } else {
//                uploadsContainer.setVisibility(View.GONE);
//            }
//        } else {
//            snatchesContainer.setVisibility(View.GONE);
//            uploadsContainer.setVisibility(View.GONE);
//        }

        if (profile.response.personal.donor) {
            donor.setVisibility(View.VISIBLE);
        }
        if (profile.response.personal.warned) {
            warned.setVisibility(View.VISIBLE);
        }
        if (!profile.response.personal.enabled) {
            banned.setVisibility(View.VISIBLE);
        }
//
//        if (profile.getRanks().getDownloaded() != null) {
//            dataDownloadedText.setVisibility(View.VISIBLE);
//            dataDownloaded.setText(profile.response.ranks.downloaded);
//            dataDownloadedValue.setVisibility(View.VISIBLE);
////            dataDownloadedValue.setText(String.format("%s ", toHumanReadableSize(profile.getStats().getDownloaded().longValue(), 3)));
//        } else {
//            dataDownloaded.setVisibility(View.GONE);
//            dataDownloadedText.setVisibility(View.GONE);
//        }
//        if (profile.getRanks().getUploaded() != null) {
//            dataUploadedText.setVisibility(View.VISIBLE);
//            dataUploaded.setText("" + profile.getRanks().getUploaded().intValue() + "%");
//            dataUploadedValue.setVisibility(View.VISIBLE);
////            dataUploadedValue.setText(String.format("%s ", toHumanReadableSize(profile.getStats().getUploaded().longValue(), 3)));
//        } else {
//            dataUploaded.setVisibility(View.GONE);
//            dataUploadedText.setVisibility(View.GONE);
//        }
//        if (profile.getRanks().getUploads() != null) {
//            torrentsUploadedText.setVisibility(View.VISIBLE);
//            torrentsUploaded.setText("" + profile.getRanks().getUploads().intValue() + "%");
//            torrentsUploadedValue.setVisibility(View.VISIBLE);
////            torrentsUploadedValue.setText("(" + profile.getCommunity().getUploaded().intValue() + ") ");
//        } else {
//            torrentsUploaded.setVisibility(View.GONE);
//            torrentsUploadedText.setVisibility(View.GONE);
//        }
//        if (profile.getRanks().getRequests() != null) {
//            requestsFilledText.setVisibility(View.VISIBLE);
//            requestsFilled.setText("" + profile.getRanks().getRequests().intValue() + "%");
//            requestsFilledValue.setVisibility(View.VISIBLE);
////            requestsFilledValue.setText("(" + profile.getCommunity().getRequestsFilled().intValue() + ") ");
//        } else {
//            requestsFilled.setVisibility(View.GONE);
//            requestsFilledText.setVisibility(View.GONE);
//        }
//        if (profile.getRanks().getBounty() != null) {
//            bountySpentText.setVisibility(View.VISIBLE);
//            bountySpent.setText("" + profile.getRanks().getBounty().intValue() + "%");
//        } else {
//            bountySpent.setVisibility(View.GONE);
//            bountySpentText.setVisibility(View.GONE);
//        }
//        if (profile.getRanks().getPosts() != null) {
//            postsMadeText.setVisibility(View.VISIBLE);
//            postsMade.setText("" + profile().getPosts().intValue() + "%");
//            postsMadeValue.setVisibility(View.VISIBLE);
//            postsMadeValue.setText("(" + profile.getCommunity().getPosts().intValue() + ") ");
//        } else {
//            postsMade.setVisibility(View.GONE);
//            postsMadeText.setVisibility(View.GONE);
//        }
//        if (profile.getRanks().getArtists() != null) {
//            artistsAddedText.setVisibility(View.VISIBLE);
//            artistsAdded.setText("" + profile.getRanks().getArtists().intValue() + "%");
//        } else {
//            artistsAdded.setVisibility(View.GONE);
//            artistsAddedText.setVisibility(View.GONE);
//        }
//        if (profile.getRanks().getOverall() != null) {
//            overallText.setVisibility(View.VISIBLE);
//            overall.setText("" + profile.getRanks().getOverall().intValue() + "%");
//        } else {
//            overall.setVisibility(View.GONE);
//            overallText.setVisibility(View.GONE);
//        }
    }

    @Override
    public void showRecents() {

    }
}
