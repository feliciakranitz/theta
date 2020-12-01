package hu.bme.mit.theta.cloud.worker;

import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;

import java.nio.file.Path;

public interface Analyser {
    void runAnalysis(JobEntity jobEntity, AnalysisProgressListener progressListener);

    interface AnalysisProgressListener {
        void onBegin();

        void onProgress(int progressPercent);

        void onComplete(boolean successful);
    }
}
